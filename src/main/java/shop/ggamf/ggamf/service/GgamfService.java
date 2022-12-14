package shop.ggamf.ggamf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import shop.ggamf.ggamf.domain.recommendBanuser.RecommendBanuser;
import shop.ggamf.ggamf.domain.recommendBanuser.RecommendBanuserRepository;
import shop.ggamf.ggamf.domain.report.Report;
import shop.ggamf.ggamf.domain.report.ReportRepository;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.room.RoomRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.GgamfReqDto.FollowGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.RecommendBanReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.ReportGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.AcceptGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.CancelGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.DeleteGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.FollowGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.GgamfListRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.ReceiveGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.RecommendBanRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.RecommendGgamfListRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.RejectGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.ReportGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.SendGgamfRespDto;

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
    private final RecommendBanuserRepository recommendBanuserRepository;

    @Transactional
    public FollowGgamfRespDto 겜프요청(FollowGgamfReqDto followGgamfReqDto, Long userId, Long friendId) {
        log.debug("디버그 : 겜프요청 서비스 호출");
        // 나
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("내 유저 정보가 없습니다", HttpStatus.FORBIDDEN));
        // 요청받은사람
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new CustomApiException("해당 유저가 없습니다", HttpStatus.FORBIDDEN));
        // 중복 요청 체크
        Follow followerPS = followRepository.isGgamfFollow(userId, friendId).orElse(null);
        Follow followingPS = followRepository.isGgamfFollow(friendId, userId).orElse(null);
        if (!(followerPS == null && followingPS == null)) {
            throw new CustomApiException("요청이 있었거나 이미 겜프입니다", HttpStatus.BAD_REQUEST);
        }
        // 추천받지않기 테이블에 있으면 삭제
        RecommendBanuser recommendBanUser = recommendBanuserRepository.findByBothId(followGgamfReqDto.getUserId(),
                followGgamfReqDto.getFriendId());
        if (recommendBanUser != null) {
            recommendBanuserRepository.deleteById(recommendBanUser.getId());
        }
        // 실행
        Follow followPS = followGgamfReqDto.toEntity(user, friend);
        Follow follow = followRepository.save(followPS);
        return new FollowGgamfRespDto(follow);
    }

    @Transactional
    public AcceptGgamfRespDto 겜프수락(Long userId, Long friendId) {
        log.debug("디버그 : 겜프수락 서비스 호출");
        Follow followPS = followRepository.findByBothId(friendId, userId)
                .orElseThrow(() -> new CustomApiException("요청이 오간 사이도, 겜프 사이도 아닙니다", HttpStatus.FORBIDDEN));
        if (followPS.getFollowing().getId() != userId) {
            throw new CustomApiException("당신이 받은 겜프 요청이 아닙니다.", HttpStatus.BAD_REQUEST);
        }
        if (followPS.getAccept() == true) {
            throw new CustomApiException("이미 겜프 사이 입니다.", HttpStatus.BAD_REQUEST);
        }
        // 실행
        followPS.acceptGgamf();
        return new AcceptGgamfRespDto(followPS);
    }

    @Transactional
    public DeleteGgamfRespDto 겜프삭제(Long userId, Long friendId) {
        log.debug("디버그 : 겜프삭제 서비스 호출");
        Follow followerPS = followRepository.isGgamfFollow(userId, friendId).orElse(null);
        Follow followingPS = followRepository.isGgamfFollow(friendId, userId).orElse(null);
        if (followerPS == null && followingPS == null) {
            throw new CustomApiException("요청이 오간 사이도, 겜프 사이도 아닙니다", HttpStatus.BAD_REQUEST);
        } else if (followerPS != null && followingPS != null) {
            throw new CustomApiException("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        if (followerPS != null && followingPS == null) {
            if (followerPS.getFollower().getId() != userId) {
                throw new CustomApiException("당신의 겜프가 아닙니다", HttpStatus.BAD_REQUEST);
            }
            if (followerPS.getAccept() != true) {
                throw new CustomApiException("겜프 사이가 아닙니다", HttpStatus.BAD_REQUEST);
            }
            followRepository.delete(followerPS);
            return new DeleteGgamfRespDto(followerPS.getFollowing());
        } else {
            if (followingPS.getFollowing().getId() != userId) {
                throw new CustomApiException("당신의 겜프가 아닙니다", HttpStatus.BAD_REQUEST);
            }
            if (followingPS.getAccept() != true) {
                throw new CustomApiException("겜프 사이가 아닙니다", HttpStatus.BAD_REQUEST);
            }
            followRepository.delete(followingPS);
            return new DeleteGgamfRespDto(followingPS.getFollower());
        }
    }

    @Transactional
    public RejectGgamfRespDto 겜프거절(Long userId, Long friendId) {
        log.debug("디버그 : 겜프거절 서비스 호출");
        Follow followPS = followRepository.findByBothId(friendId, userId)
                .orElseThrow(() -> new CustomApiException("요청이 오간 사이도, 겜프 사이도 아닙니다", HttpStatus.FORBIDDEN));
        if (followPS.getFollowing().getId() != userId) {
            throw new CustomApiException("당신이 받은 겜프 요청이 아닙니다", HttpStatus.BAD_REQUEST);
        }
        if (followPS.getAccept() == true) {
            throw new CustomApiException("이미 겜프입니다. 삭제를 원하면 겜프 프로필에서 삭제하세요", HttpStatus.BAD_REQUEST);
        }
        followRepository.delete(followPS);
        return new RejectGgamfRespDto(followPS.getFollower());
    }

    @Transactional
    public CancelGgamfRespDto 겜프요청취소(Long userId, Long friendId) {
        log.debug("디버그 : 겜프요청취소 서비스 호출");
        Follow followPS = followRepository.findByBothId(userId, friendId)
                .orElseThrow(() -> new CustomApiException("요청이 오간 사이도, 겜프 사이도 아닙니다", HttpStatus.FORBIDDEN));
        if (!(followPS.getFollower().getId() == userId)) {
            throw new CustomApiException("당신이 보낸 겜프 요청이 아닙니다", HttpStatus.BAD_REQUEST);
        }
        if (followPS.getAccept() == true) {
            throw new CustomApiException("이미 겜프입니다. 삭제를 원하면 겜프 삭제하세요", HttpStatus.BAD_REQUEST);
        }
        if (followPS.getFollower().getId() != userId) {
            throw new CustomApiException("당신이 요청한 것이 아닙니다.", HttpStatus.BAD_REQUEST);
        }
        followRepository.delete(followPS);
        return new CancelGgamfRespDto(followPS.getFollowing());
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

    public SendGgamfRespDto 보낸겜프요청목록보기(Long userId) {
        User userPS = userRepository.findById(userId)
                .orElseThrow(
                        () -> new CustomApiException("요청 유저가 없습니다", HttpStatus.FORBIDDEN));
        // 내가 보낸 요청 중에서 아직 수락되지 않은 요청 목록
        List<Follow> followListPS = followRepository.findByUserIdFollower(userId);
        return new SendGgamfRespDto(followListPS);
    }

    public ReceiveGgamfRespDto 받은겜프요청목록보기(Long userId) {
        User userPS = userRepository.findById(userId)
                .orElseThrow(
                        () -> new CustomApiException("요청 유저가 없습니다", HttpStatus.FORBIDDEN));
        List<Follow> followListPS = followRepository.findByUserIdFollowing(userId);
        return new ReceiveGgamfRespDto(followListPS);
    }

    public RecommendGgamfListRespDto 추천겜프목록보기(Long userId) {
        // 나 검증
        User userPS = userRepository.findById(userId)
                .orElseThrow(
                        () -> new CustomApiException("요청 유저가 없습니다", HttpStatus.FORBIDDEN));
        // <내가 방장일 때>
        // 가장 최근 종료한 방 찾기
        List<Room> roomList = roomRepository.findByUserIdEnd(userId);
        // 방 종료까지 함께한 인원 셀렉하기
        List<Enter> latestList = enterRepository.findByRoomIdEnd(roomList.get(0).getId());
        log.debug("디버그 : latestList.size() : " + latestList.size());
        List<Long> latestIdList = new ArrayList<>();
        for (int i = 0; i < latestList.size(); i++) {
            latestIdList.add(latestList.get(i).getUser().getId());
        }
        // <내가 참여했을 때>
        // 내가 참여했던 방
        List<Enter> enterRoomList = enterRepository.findEnterRoom(userId);
        log.debug("디버그 : enterRoomList.size() : " + enterRoomList.size());
        List<Long> enterRoomIdList = new ArrayList<>();
        for (int i = 0; i < enterRoomList.size(); i++) {
            enterRoomIdList.add(enterRoomList.get(i).getRoom().getId());
        }
        log.debug("디버그 : enterRoomIdList.size() : " + enterRoomIdList.size());
        // 방 출입 유저 id 목록
        List<Enter> enterUserList = enterRepository.findTogether(userId, enterRoomIdList);
        List<Long> enterUserIdList = new ArrayList<>();
        for (int i = 0; i < enterUserList.size(); i++) {
            enterUserIdList.add(enterUserList.get(i).getUser().getId());
        }
        log.debug("디버그 : enterUserIdList.size() : " + enterUserIdList.size());
        // 두 리스트 합치기
        List<Long> recommendFriendList = new ArrayList<>();
        recommendFriendList.addAll(latestIdList);
        recommendFriendList.addAll(enterUserIdList);
        log.debug("디버그 : recommendFriendList.size() : " + recommendFriendList.size());
        List<Long> userList = recommendFriendList.stream().distinct().collect(Collectors.toList());
        log.debug("디버그 : userList.size() : " + userList.size());

        // 합친 리스트 친구, 친구 신청 여부 확인 팔로잉=친구 or 팔로워=친구
        List<Follow> friendFollowingLatest = followRepository.findByRecommendFollowing(userId, userList);
        for (int i = 0; i < friendFollowingLatest.size(); i++) {
            if (userList.contains(friendFollowingLatest.get(i).getFollowing().getId())) {
                userList.remove(friendFollowingLatest.get(i).getFollowing().getId());
            }
        }
        List<Follow> friendFollowerLatest = followRepository.findByRecommendFollower(userId, userList);
        for (int i = 0; i < friendFollowerLatest.size(); i++) {
            if (userList.contains(friendFollowerLatest.get(i).getFollower().getId())) {
                userList.remove(friendFollowerLatest.get(i).getFollower().getId());
            }
        }
        log.debug("디버그 : userList.size() : " + userList.size());
        // 합친 리스트 추천받지않도록 설정되어있는지 확인
        List<RecommendBanuser> banList = recommendBanuserRepository.findByUserId(userId);
        for (int i = 0; i < banList.size(); i++) {
            if (userList.contains(banList.get(i).getBanuser().getId())) {
                userList.remove(banList.get(i).getBanuser().getId());
            }
        }
        log.debug("디버그 : userList.size() : " + userList.size());
        // 친구 추천하기
        List<User> recommendList = userRepository.findByIdRecommend(userList);
        return new RecommendGgamfListRespDto(recommendList);
    }

    @Transactional
    public RecommendBanRespDto 추천겜프삭제(RecommendBanReqDto recommendBanReqDto, Long userId, Long banuserId) {
        User userPS = userRepository.findById(userId)
                .orElseThrow(
                        () -> new CustomApiException("요청 유저가 없습니다", HttpStatus.FORBIDDEN));
        User banuserPS = userRepository.findById(banuserId)
                .orElseThrow(
                        () -> new CustomApiException("해당 유저가 없습니다", HttpStatus.FORBIDDEN));
        if (recommendBanuserRepository.findByBothId(userId, banuserId) != null) {
            throw new CustomApiException("이미 추천받지 않게 설정된 유저입니다", HttpStatus.BAD_REQUEST);
        }
        RecommendBanuser recommendBanuserPS = recommendBanReqDto.toEntity(userPS, banuserPS);
        RecommendBanuser recommendBanUser = recommendBanuserRepository.save(recommendBanuserPS);
        return new RecommendBanRespDto(recommendBanUser);
    }

}