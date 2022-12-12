package shop.ggamf.ggamf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.exception.CustomApiException;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.enter.EnterRepository;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.follow.FollowRepository;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCode;
import shop.ggamf.ggamf.domain.reasonCode.ReasonCodeRepository;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.report.ReportRepository;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.room.RoomRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.GgamfReqDto.AcceptGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.FollowGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.ReportGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.AcceptGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.CancelGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.DeleteGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.FollowGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.GgamfListRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.RecommendGgamfListRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.RejectGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.ReportGgamfRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GgamfService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final ReasonCodeRepository reasonCodeRepository;
    private final EnterRepository enterRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public FollowGgamfRespDto 겜프요청(FollowGgamfReqDto followGgamfReqDto) {
        log.debug("디버그 : 겜프요청 서비스 호출");
        // 나
        User follower = userRepository.findById(followGgamfReqDto.getFollowerId())
                .orElseThrow(() -> new CustomApiException("내 유저 정보가 없습니다", HttpStatus.FORBIDDEN));
        // 요청받은사람
        User following = userRepository.findById(followGgamfReqDto.getFollowingId())
                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.FORBIDDEN));
        // // 검증 - 고민중
        // if (followRepository.findFollow(followGgamfReqDto.getFollowerId(),
        // followGgamfReqDto.getFollowingId()) != null) {
        // throw new CustomApiException("당신은 이미 요청했습니다. 기다리세요.",
        // HttpStatus.BAD_REQUEST);
        // } else if (followRepository.findFollow(followGgamfReqDto.getFollowingId(),
        // followGgamfReqDto.getFollowerId()) != null) {
        // throw new CustomApiException("상대가 이미 요청했습니다.", HttpStatus.BAD_REQUEST);
        // }
        Follow follow = followGgamfReqDto.toEntity(follower, following);
        Follow followPS = followRepository.save(follow);
        return new FollowGgamfRespDto(followPS);
    }

    @Transactional
    public AcceptGgamfRespDto 겜프수락(AcceptGgamfReqDto acceptGgamfReqDto) {
        log.debug("디버그 : 겜프수락 서비스 호출");
        Follow followPS = followRepository.findById(acceptGgamfReqDto.getFollowId())
                .orElseThrow(() -> new CustomApiException("겜프 신청 중이 아닙니다", HttpStatus.FORBIDDEN));
        if (followPS.getFollowing().getId() != acceptGgamfReqDto.getUserId()) {
            throw new CustomApiException("당신은 수락 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        followPS.acceptGgamf();
        return new AcceptGgamfRespDto(followPS);
    }

    @Transactional
    public DeleteGgamfRespDto 겜프삭제(Long userId, Long followId) {
        log.debug("디버그 : 겜프삭제 서비스 호출");
        Follow followPS = followRepository.findById(followId)
                .orElseThrow(() -> new CustomApiException("삭제할 겜프가 없습니다", HttpStatus.FORBIDDEN));
        if (followPS.getAccept() != true) {
            throw new CustomApiException("겜프 사이가 아닙니다", HttpStatus.BAD_REQUEST);
        }
        followRepository.delete(followPS);
        return new DeleteGgamfRespDto(followId);
    }

    @Transactional
    public RejectGgamfRespDto 겜프거절(Long userId, Long followId) {
        log.debug("디버그 : 겜프거절 서비스 호출");
        Follow followPS = followRepository.findById(followId)
                .orElseThrow(() -> new CustomApiException("거절 할 겜프 요청이 없습니다", HttpStatus.FORBIDDEN));
        if (followPS.getAccept() == true) {
            throw new CustomApiException("이미 겜프입니다. 삭제를 원하면 겜프 프로필에서 삭제하세요", HttpStatus.BAD_REQUEST);
        }
        if (followPS.getFollowing().getId() != userId) {
            throw new CustomApiException("당신은 거절 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        followRepository.delete(followPS);
        return new RejectGgamfRespDto(followId);
    }

    @Transactional
    public CancelGgamfRespDto 겜프요청취소(Long userId, Long followId) {
        log.debug("디버그 : 겜프요청취소 서비스 호출");
        Follow followPS = followRepository.findById(followId)
                .orElseThrow(() -> new CustomApiException("취소 할 겜프 요청이 없습니다", HttpStatus.FORBIDDEN));
        if (followPS.getAccept() == true) {
            throw new CustomApiException("이미 겜프입니다. 삭제를 원하면 겜프 프로필에서 삭제하세요", HttpStatus.BAD_REQUEST);
        }
        if (followPS.getFollower().getId() != userId) {
            throw new CustomApiException("당신은 취소 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        followRepository.delete(followPS);
        return new CancelGgamfRespDto(followId);
    }

    @Transactional
    public ReportGgamfRespDto 겜프신고(ReportGgamfReqDto reportGgamfReqDto) {
        log.debug("디버그 : 겜프신고 서비스 호출");
        // 신고하는자
        User follower = userRepository.findById(reportGgamfReqDto.getUserId())
                .orElseThrow(() -> new CustomApiException("내 정보가 없습니다", HttpStatus.FORBIDDEN));
        // 신고당하는자
        User following = userRepository.findById(reportGgamfReqDto.getBadUserId())
                .orElseThrow(() -> new CustomApiException("신고할 유저 정보가 없습니다", HttpStatus.FORBIDDEN));
        // 신고내용코드
        ReasonCode reasonCode = reasonCodeRepository.findById(reportGgamfReqDto.getReasonCodeId())
                .orElseThrow(() -> new CustomApiException("신고 내용 코드가 없습니다", HttpStatus.FORBIDDEN));
        // 실행
        Report report = reportGgamfReqDto.toEntity(follower, following, reasonCode);
        Report reportPS = reportRepository.save(report);
        return new ReportGgamfRespDto(reportPS);
    }

    public GgamfListRespDto 겜프목록보기(Long userId) {
        // 내가 요청해서 맺은 친구 목록
        List<Follow> followerListPS = followRepository.findByFollowerId(userId);
        // 내가 수락해서 맺은 친구 목록
        List<Follow> followingListPS = followRepository.findByFollowingId(userId);
        return new GgamfListRespDto(followerListPS, followingListPS);
    }

    public RecommendGgamfListRespDto 추천겜프목록보기(Long userId) {
        // <내가 방장일 때>
        // 가장 최근 방 찾기
        List<Room> roomListPS = roomRepository.findByUserIdEnd(userId);
        log.debug("디버그 : roomPS" + roomListPS.get(0).getId());
        // 방 종료까지 함께한 인원 셀렉하기
        List<Enter> latestPS = enterRepository.findByRoomIdEnd(roomListPS.get(0).getId());
        log.debug("디버그 : latestPS : " + latestPS.get(0).getUser().getId());
        List<Long> latestIdList = new ArrayList<>();
        for (int i = 0; i < latestPS.size(); i++) {
            latestIdList.add(latestPS.get(i).getUser().getId());
        }
        log.debug("디버그 : latestIdList : " + latestIdList.size());
        log.debug("디버그 : latestIdList : " + latestIdList.size());
        // 중복제거
        List<Long> latestIdListPS = latestIdList.stream().distinct().collect(Collectors.toList());
        log.debug("디버그 : latestIdListPS.size() : " + latestIdListPS.size());

        // 친구, 친구 신청 여부 확인 팔로잉=친구 or 팔로워=친구
        List<Follow> friendFollowingLatest = followRepository.findByRecommendFollowing(userId, latestIdListPS);
        List<Follow> friendFollowerLatest = followRepository.findByRecommendFollower(userId, latestIdListPS);
        for (int i = 0; i < friendFollowingLatest.size(); i++) {
            if (latestIdListPS.contains(friendFollowingLatest.get(i).getFollowing().getId())) {
                latestIdListPS.remove(friendFollowingLatest.get(i).getFollowing().getId());
            }
        }
        for (int i = 0; i < friendFollowerLatest.size(); i++) {
            if (latestIdListPS.contains(friendFollowerLatest.get(i).getFollower().getId())) {
                latestIdListPS.remove(friendFollowerLatest.get(i).getFollower().getId());
            }
        }
        // 친구 추천하기
        List<User> recommendLatest = userRepository.findByIdForRecommend(latestIdListPS);

        // <내가 참여했을 때>
        // 내가 참여했던 방
        List<Enter> enterRoomListPS = enterRepository.findEnterRoom(userId);
        log.debug("디버그 : enterRoomListPS.size() : " + enterRoomListPS.size());
        List<Long> enterRoomIdList = new ArrayList<>();
        for (int i = 0; i < enterRoomListPS.size(); i++) {
            enterRoomIdList.add(enterRoomListPS.get(i).getRoom().getId());
        }
        log.debug("디버그 : enterRoomIdList.size() : " + enterRoomIdList.size());

        // 방 들어왔다 나간 사람 id 목록
        List<Enter> enterListPS = enterRepository.findTogether(userId, enterRoomIdList);
        log.debug("디버그 : enterListPS.size() : " + enterListPS.size());
        List<Long> enterUserIdList = new ArrayList<>();
        for (int i = 0; i < enterListPS.size(); i++) {
            enterUserIdList.add(enterListPS.get(i).getUser().getId());
        }
        log.debug("디버그 : enterUserIdList.size() : " + enterUserIdList.size());
        // 중복 제거
        List<Long> enterUserIdListPS = enterUserIdList.stream().distinct().collect(Collectors.toList());
        log.debug("디버그 : enterUserIdListPS.size() : " + enterUserIdListPS.size());

        // 친구, 친구 신청 여부 확인 팔로잉=친구 or 팔로워=친구
        List<Follow> friendFollowingEnter = followRepository.findByRecommendFollowing(userId, enterUserIdListPS);
        log.debug("디버그 : friendFollowingEnter : " + friendFollowingEnter.size());
        log.debug("디버그 : friendFollowingEnter : " + friendFollowingEnter.get(0).getFollowing().getUsername());
        List<Follow> friendFollowerEnter = followRepository.findByRecommendFollower(userId, enterUserIdListPS);
        log.debug("디버그 : friendFollowerEnter : " + friendFollowerEnter.size());
        log.debug("디버그 : friendFollowerEnter : " + friendFollowerEnter.get(0).getFollower().getUsername());
        for (int i = 0; i < friendFollowingEnter.size(); i++) {
            if (enterUserIdListPS.contains(friendFollowingEnter.get(i).getFollowing().getId())) {
                enterUserIdListPS.remove(friendFollowingEnter.get(i).getFollowing().getId());
            }
        }
        for (int i = 0; i < friendFollowerEnter.size(); i++) {
            if (enterUserIdListPS.contains(friendFollowerEnter.get(i).getFollower().getId())) {
                enterUserIdListPS.remove(friendFollowerEnter.get(i).getFollower().getId());
            }
        }

        // 친구 추천하기
        List<User> recommendEnter = userRepository.findByIdForRecommend(enterUserIdListPS);

        return new RecommendGgamfListRespDto(recommendLatest, recommendEnter);
    }
}