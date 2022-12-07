package shop.ggamf.ggamf.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.exception.CustomApiException;
import shop.ggamf.ggamf.domain.follow.Follow;
import shop.ggamf.ggamf.domain.follow.FollowRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.GgamfReqDto.AcceptGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.FollowGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.AcceptGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.FollowGgamfRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GgamfService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public FollowGgamfRespDto 겜프요청(FollowGgamfReqDto followGgamfReqDto) {
        log.debug("디버그 : 파티방생성 서비스 호출");
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
        Follow followPS = followRepository.findById(acceptGgamfReqDto.getFollowId())
                .orElseThrow(() -> new CustomApiException("겜프 신청 중이 아닙니다", HttpStatus.FORBIDDEN));
        if (followPS.getFollowing().getId() != acceptGgamfReqDto.getUserId()) {
            throw new CustomApiException("당신은 수락 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        followPS.acceptGgamf();
        return new AcceptGgamfRespDto(followPS);
    }

}
