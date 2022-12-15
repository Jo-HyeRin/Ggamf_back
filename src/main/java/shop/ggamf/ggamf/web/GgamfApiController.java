package shop.ggamf.ggamf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.annotations.AuthorizationCheck;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.exception.CustomApiException;
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
import shop.ggamf.ggamf.dto.ResponseDto;
import shop.ggamf.ggamf.service.GgamfService;

@RequestMapping("/s/api")
@RequiredArgsConstructor
@RestController
public class GgamfApiController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final GgamfService ggamfService;

    // 겜프 요청
    @AuthorizationCheck
    @PostMapping("/ggamf/user/{userId}/follow/{friendId}")
    public ResponseEntity<?> followGgamf(@RequestBody FollowGgamfReqDto followGgamfReqDto,
            @PathVariable Long userId, @PathVariable Long friendId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 요청 컨트롤러 호출");
        FollowGgamfRespDto followGgamfRespDto = ggamfService.겜프요청(followGgamfReqDto, userId, friendId);
        return new ResponseEntity<>(new ResponseDto<>("겜프 요청 완료", followGgamfRespDto), HttpStatus.CREATED);
    }

    // 겜프 수락
    @AuthorizationCheck
    @PutMapping("/ggamf/user/{userId}/accept/{friendId}")
    public ResponseEntity<?> acceptGgamf(@PathVariable Long userId, @PathVariable Long friendId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 수락 컨트롤러 호출");
        AcceptGgamfRespDto acceptGgamfRespDto = ggamfService.겜프수락(userId, friendId);
        return new ResponseEntity<>(new ResponseDto<>("겜프 수락 완료", acceptGgamfRespDto), HttpStatus.CREATED);
    }

    // 겜프 삭제
    @AuthorizationCheck
    @DeleteMapping("/ggamf/user/{userId}/unfollow/{friendId}")
    public ResponseEntity<?> deleteGgamf(@PathVariable Long userId, @PathVariable Long friendId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 삭제 컨트롤러 호출");
        DeleteGgamfRespDto deleteGgamfRespDto = ggamfService.겜프삭제(userId, friendId);
        return new ResponseEntity<>(new ResponseDto<>("겜프 삭제 완료", deleteGgamfRespDto), HttpStatus.OK);
    }

    // 겜프 거절
    @AuthorizationCheck
    @DeleteMapping("/ggamf/user/{userId}/reject/{friendId}")
    public ResponseEntity<?> rejectGgamf(@PathVariable Long userId, @PathVariable Long friendId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 거절 컨트롤러 호출");
        RejectGgamfRespDto rejectGgamfRespDto = ggamfService.겜프거절(userId, friendId);
        return new ResponseEntity<>(new ResponseDto<>("겜프 거절 완료", rejectGgamfRespDto), HttpStatus.OK);
    }

    // 겜프 요청 취소
    @AuthorizationCheck
    @DeleteMapping("/ggamf/user/{userId}/cancel/{friendId}")
    public ResponseEntity<?> cancelGgamf(@PathVariable Long userId, @PathVariable Long friendId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 요청 취소 컨트롤러 호출");
        CancelGgamfRespDto cancelGgamfRespDto = ggamfService.겜프요청취소(userId, friendId);
        return new ResponseEntity<>(new ResponseDto<>("겜프요청취소 완료", cancelGgamfRespDto), HttpStatus.OK);
    }

    // 겜프 신고
    @AuthorizationCheck
    @PostMapping("/ggamf/user/{userId}/report/{badUserId}")
    public ResponseEntity<?> reportGgamf(@RequestBody ReportGgamfReqDto reportGgamfReqDto,
            @PathVariable Long userId,
            @PathVariable Long badUserId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 신고 컨트롤러 호출");
        ReportGgamfRespDto reportGgamfRespDto = ggamfService.겜프신고(reportGgamfReqDto, userId, badUserId);
        return new ResponseEntity<>(new ResponseDto<>("겜프신고 완료", reportGgamfRespDto), HttpStatus.CREATED);
    }

    // 겜프 목록 보기
    @AuthorizationCheck
    @GetMapping("/ggamf/user/{userId}/list")
    public ResponseEntity<?> findGgamfList(@PathVariable Long userId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 겜프 목록 보기 컨트롤러 호출");
        GgamfListRespDto ggamfListRespDto = ggamfService.겜프목록보기(userId);
        return new ResponseEntity<>(new ResponseDto<>("겜프목록보기 완료", ggamfListRespDto),
                HttpStatus.OK);
    }

    // 내가 보낸 겜프 요청 목록 보기
    @AuthorizationCheck
    @GetMapping("/ggamf/user/{userId}/sendggamf")
    public ResponseEntity<?> sendGgamfList(@PathVariable Long userId, @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 보낸 겜프 요청 목록 보기 컨트롤러 호출");
        SendGgamfRespDto sendGgamfRespDto = ggamfService.보낸겜프요청목록보기(userId);
        return new ResponseEntity<>(new ResponseDto<>("보낸겜프요청목록보기 완료", sendGgamfRespDto),
                HttpStatus.OK);
    }

    // 내가 받은 겜프 요청 목록 보기
    @AuthorizationCheck
    @GetMapping("/ggamf/user/{userId}/receiveggamf")
    public ResponseEntity<?> receiveGgamfList(@PathVariable Long userId, @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 받은 겜프 요청 목록 보기 컨트롤러 호출");
        ReceiveGgamfRespDto receiveGgamfRespDto = ggamfService.받은겜프요청목록보기(userId);
        return new ResponseEntity<>(new ResponseDto<>("받은겜프요청목록보기 완료", receiveGgamfRespDto),
                HttpStatus.OK);
    }

    // 추천 겜프 목록 보기
    @AuthorizationCheck
    @GetMapping("/ggamf/user/{userId}/recommend")
    public ResponseEntity<?> recommendGgamfList(@PathVariable Long userId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 추천 겜프 목록 보기 컨트롤러 호출");
        RecommendGgamfListRespDto recommendGgamfListRespDto = ggamfService.추천겜프목록보기(userId);
        return new ResponseEntity<>(new ResponseDto<>("추천겜프목록보기 완료", recommendGgamfListRespDto), HttpStatus.OK);
    }

    // 추천 겜프 삭제 = RecommendBanuser 테이블 insert
    @AuthorizationCheck
    @PostMapping("/ggamf/user/{userId}/recommendban/{banuserId}")
    public ResponseEntity<?> recommendBan(@RequestBody RecommendBanReqDto recommendBanReqDto, @PathVariable Long userId,
            @PathVariable Long banuserId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 추천 겜프 삭제 컨트롤러 호출");
        RecommendBanRespDto recommendBanRespDto = ggamfService.추천겜프삭제(recommendBanReqDto, userId, banuserId);
        return new ResponseEntity<>(new ResponseDto<>("추천겜프삭제 완료", recommendBanRespDto), HttpStatus.CREATED);
    }

}
