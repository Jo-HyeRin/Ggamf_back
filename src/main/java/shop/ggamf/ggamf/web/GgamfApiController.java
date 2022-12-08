package shop.ggamf.ggamf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.exception.CustomApiException;
import shop.ggamf.ggamf.dto.GgamfReqDto.AcceptGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfReqDto.FollowGgamfReqDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.AcceptGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.DeleteGgamfRespDto;
import shop.ggamf.ggamf.dto.GgamfRespDto.FollowGgamfRespDto;
import shop.ggamf.ggamf.dto.ResponseDto;
import shop.ggamf.ggamf.service.GgamfService;

@RequestMapping("/s/api")
@RequiredArgsConstructor
@RestController
public class GgamfApiController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final GgamfService ggamfService;

    // 겜프 요청
    @PostMapping("/ggamf/follow/{followingId}")
    public ResponseEntity<?> followGgamf(@RequestBody FollowGgamfReqDto followGgamfReqDto,
            @PathVariable Long followingId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 요청 컨트롤러 호출");
        followGgamfReqDto.setFollowerId(loginUser.getUser().getId());
        followGgamfReqDto.setFollowingId(followingId);
        FollowGgamfRespDto followGgamfRespDto = ggamfService.겜프요청(followGgamfReqDto);
        return new ResponseEntity<>(new ResponseDto<>("겜프 요청 완료", followGgamfRespDto), HttpStatus.CREATED);
    }

    // 겜프 수락
    @PutMapping("/ggamf/accept/{followId}")
    public ResponseEntity<?> acceptGgamf(@RequestBody AcceptGgamfReqDto acceptGgamfReqDto,
            @PathVariable Long followId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 수락 컨트롤러 호출");
        acceptGgamfReqDto.setFollowId(followId);
        acceptGgamfReqDto.setUserId(loginUser.getUser().getId());
        AcceptGgamfRespDto acceptGgamfRespDto = ggamfService.겜프수락(acceptGgamfReqDto);
        return new ResponseEntity<>(new ResponseDto<>("겜프 수락 완료", acceptGgamfRespDto), HttpStatus.CREATED);
    }

    // 겜프 삭제
    @DeleteMapping("ggamf/user/{userId}/unfollow/{followId}")
    public ResponseEntity<?> deleteGgamf(@PathVariable Long userId, @PathVariable Long followId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 삭제 컨트롤러 호출");
        if (loginUser.getUser().getId() != userId) {
            throw new CustomApiException("당신은 삭제 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        DeleteGgamfRespDto DeleteGgamfRespDto = ggamfService.겜프삭제(userId, followId);
        return new ResponseEntity<>(new ResponseDto<>("겜프 삭제 완료", DeleteGgamfRespDto), HttpStatus.OK);
    }

}
