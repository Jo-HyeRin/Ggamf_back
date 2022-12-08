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
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.dto.ResponseDto;
import shop.ggamf.ggamf.dto.UserReqDto.JoinReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateStateReqDto;
import shop.ggamf.ggamf.dto.UserRespDto.JoinRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdateRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdateStateRespDto;
import shop.ggamf.ggamf.service.UserService;

@RequestMapping("/s/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinReqDto joinReqDto) {
        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>("회원가입성공", joinRespDto), HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}/update")
    public ResponseEntity<?> update(@PathVariable Long userId, @RequestBody UpdateReqDto updateReqDto,
            @AuthenticationPrincipal LoginUser loginUser) {
        if (userId != loginUser.getUser().getId()) {
            return new ResponseEntity<>(new ResponseDto<>("권한이 없습니다", null), HttpStatus.FORBIDDEN);
        }
        updateReqDto.setId(userId);
        UpdateRespDto updateRespDto = userService.회원정보수정(updateReqDto);
        return new ResponseEntity<>(new ResponseDto<>("회원정보수정성공", updateRespDto), HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long userId, @RequestBody UpdateStateReqDto updateStateReqDto) {
        updateStateReqDto.setId(userId);
        UpdateStateRespDto updateStateRespDto = userService.회원탈퇴(updateStateReqDto);
        return new ResponseEntity<>(new ResponseDto<>("회원탈퇴성공", updateStateRespDto), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/detail")
    public ResponseEntity<?> detail(@PathVariable Long userId) {
        log.debug("디버그 : controller id : "+ userId);
        return new ResponseEntity<>(new ResponseDto<>("유저 상세보기 성공", userService.유저상세보기(userId)), HttpStatus.OK);
    }

    // @DeleteMapping("/user/{userId}/delete") //관리자만 가능
    // public ResponseEntity<?> delete(@PathVariable Long userId) {
    //     userService.회원영구삭제(userId);
    //     return new ResponseEntity<>(new ResponseDto<>("회원삭제성공", null), HttpStatus.OK);
    // }
}
