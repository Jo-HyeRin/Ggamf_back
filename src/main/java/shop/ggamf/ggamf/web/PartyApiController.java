package shop.ggamf.ggamf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.dto.PartyReqDto.CreateRoomReqDto;
import shop.ggamf.ggamf.dto.PartyRespDto.CreateRoomRespDto;
import shop.ggamf.ggamf.dto.ResponseDto;
import shop.ggamf.ggamf.service.PartyService;

@RequestMapping("/s/api")
@RequiredArgsConstructor
@RestController
public class PartyApiController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final PartyService partyService;

    // 파티방 생성
    @PostMapping("/party/create/{gameCodeId}")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomReqDto createRoomReqDto, @PathVariable Long gameCodeId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티방 생성 컨트롤러 호출");
        createRoomReqDto.setUserId(loginUser.getUser().getId());
        createRoomReqDto.setGameCodeId(gameCodeId);
        CreateRoomRespDto createRoomRespDto = partyService.파티방생성(createRoomReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티방 생성 완료", createRoomRespDto), HttpStatus.CREATED);
    }

}
