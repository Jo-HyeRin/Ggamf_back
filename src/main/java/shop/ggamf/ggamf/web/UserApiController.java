package shop.ggamf.ggamf.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.dto.ResponseDto;
import shop.ggamf.ggamf.dto.UserReqDto.JoinReqDto;
import shop.ggamf.ggamf.dto.UserReqDto.UpdateReqDto;
import shop.ggamf.ggamf.dto.UserRespDto.JoinRespDto;
import shop.ggamf.ggamf.dto.UserRespDto.UpdateRespDto;
import shop.ggamf.ggamf.service.UserService;

@RequestMapping("/s/api")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinReqDto joinReqDto) {
        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>("회원가입성공", joinRespDto), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}/update")
    public ResponseEntity<?> update(@RequestBody UpdateReqDto updateReqDto, @PathVariable Long id) {
        UpdateRespDto updateRespDto = userService.회원정보수정(updateReqDto, id);
        return new ResponseEntity<>(new ResponseDto<>("회원정보수정성공", updateRespDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}/delete")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        userService.회원영구삭제(id);
        return new ResponseEntity<>(new ResponseDto<>("회원삭제성공", null), HttpStatus.CREATED);
    }

}
