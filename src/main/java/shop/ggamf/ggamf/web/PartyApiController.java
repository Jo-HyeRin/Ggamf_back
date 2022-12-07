package shop.ggamf.ggamf.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.dto.PartyReqDto.CreateRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.EndRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.ExitRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.JoinRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.KickUserReqDto;
import shop.ggamf.ggamf.dto.PartyRespDto.CreateRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.DetailRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.EndRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.ExitRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.JoinRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.KickUserRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.RoomListByMyIdRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.RoomListRespDto;
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
        log.debug("디버그 : userId : " + loginUser.getUser().getId());
        createRoomReqDto.setUserId(loginUser.getUser().getId());
        createRoomReqDto.setGameCodeId(gameCodeId);
        log.debug("디버그 : userId : " + loginUser.getUser().getId());
        CreateRoomRespDto createRoomRespDto = partyService.파티방생성(createRoomReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티방 생성 완료", createRoomRespDto), HttpStatus.CREATED);
    }

    // 파티방 참가
    @PostMapping("/party/join/{roomId}")
    public ResponseEntity<?> joinRoom(@RequestBody JoinRoomReqDto joinRoomReqDto, @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티방 참가 컨트롤러 호출");
        joinRoomReqDto.setUserId(loginUser.getUser().getId());
        joinRoomReqDto.setRoomId(roomId);
        JoinRoomRespDto joinRoomRespDto = partyService.파티방참가(joinRoomReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티방 참가 완료", joinRoomRespDto), HttpStatus.CREATED);
    }

    // 파티방 나가기 (본인)
    @PutMapping("/party/exit/{roomId}")
    public ResponseEntity<?> exitRoom(@RequestBody ExitRoomReqDto exitRoomReqDto, @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티방 나가기 컨트롤러 호출");
        exitRoomReqDto.setRoomId(roomId);
        exitRoomReqDto.setUserId(loginUser.getUser().getId());
        ExitRoomRespDto exitRoomRespDto = partyService.파티방나가기(exitRoomReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티방 나가기 완료", exitRoomRespDto), HttpStatus.OK);
    }

    // 파티방 종료(방장)
    @PutMapping("/party/end/{roomId}")
    public ResponseEntity<?> endRoom(@RequestBody EndRoomReqDto endRoomReqDto, @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티방 종료 컨트롤러 호출");
        endRoomReqDto.setRoomId(roomId);
        endRoomReqDto.setUserId(loginUser.getUser().getId());
        EndRoomRespDto endRoomRespDto = partyService.파티방종료(endRoomReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티방 종료 완료", endRoomRespDto), HttpStatus.OK);
    }

    // 파티원 추방(방장)
    @PutMapping("/party/kick/{roomId}")
    public ResponseEntity<?> kickUser(@RequestBody KickUserReqDto kickUserReqDto, @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티원 추방 컨트롤러 호출");
        kickUserReqDto.setUserId(loginUser.getUser().getId());
        kickUserReqDto.setRoomId(roomId);
        KickUserRespDto kickUserRespDto = partyService.파티원추방(kickUserReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티원 추방 완료", kickUserRespDto), HttpStatus.OK);
    }

    // 나의 모집 파티 목록
    @GetMapping("/party/myrooms")
    public ResponseEntity<?> findByMyIdRoom(@AuthenticationPrincipal LoginUser loginUser) {
        RoomListByMyIdRespDto roomListByMyIdRespDto = partyService.나의모집파티목록(loginUser.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>("나의 모집 파티 목록 보기 완료", roomListByMyIdRespDto), HttpStatus.OK);
    }

    // 파티방 상세보기
    @GetMapping("/party/{roomId}")
    public ResponseEntity<?> detailRoom(@PathVariable Long roomId) {
        DetailRoomRespDto detailRoomRespDto = partyService.파티방상세보기(roomId);
        return new ResponseEntity<>(new ResponseDto<>("파티방 상세보기 완료", detailRoomRespDto), HttpStatus.OK);
    }

    // 전체 파티방 목록 보기 -> 카테고리별, 페이징, 검색 필요
    @GetMapping("/party/list")
    public ResponseEntity<?> findAllRoom() {
        RoomListRespDto roomListRespDto = partyService.전체파티방목록보기();
        return new ResponseEntity<>(new ResponseDto<>("전체 파티방 목록 보기 완료", roomListRespDto), HttpStatus.OK);
    }

}
