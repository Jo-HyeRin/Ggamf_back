package shop.ggamf.ggamf.web;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.ggamf.ggamf.config.annotations.AuthorizationCheck;
import shop.ggamf.ggamf.config.auth.LoginUser;
import shop.ggamf.ggamf.config.exception.CustomApiException;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.dto.PartyReqDto.CreateRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.JoinRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.KickUserReqDto;
import shop.ggamf.ggamf.dto.PartyRespDto.CreateRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.DetailRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.EndRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.ExitRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.JoinRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.KickUserRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.RoomListByIdRespDto;
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
    @AuthorizationCheck
    @GetMapping("/party/user/{userId}/create")
    public ResponseEntity<?> createRoomView(@PathVariable Long userId,
            @AuthenticationPrincipal LoginUser loginUser) {
        List<GameCode> gameCodeList = partyService.파티방생성화면게임코드전달();
        return new ResponseEntity<>(new ResponseDto<>("파티방생성화면 게임코드 전달 완료", gameCodeList), HttpStatus.OK);
    }

    @AuthorizationCheck
    @PostMapping("/party/user/{userId}/create/{gameCodeId}")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomReqDto createRoomReqDto, @PathVariable Long userId,
            @PathVariable Long gameCodeId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티방 생성 컨트롤러 호출");
        createRoomReqDto.setUserId(userId);
        createRoomReqDto.setGameCodeId(gameCodeId);
        CreateRoomRespDto createRoomRespDto = partyService.파티방생성(createRoomReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티방 생성 완료", createRoomRespDto), HttpStatus.CREATED);
    }

    // 파티방 참가
    @AuthorizationCheck
    @PostMapping("/party/user/{userId}/join/{roomId}")
    public ResponseEntity<?> joinRoom(@RequestBody JoinRoomReqDto joinRoomReqDto, @PathVariable Long userId,
            @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티방 참가 컨트롤러 호출");
        joinRoomReqDto.setUserId(userId);
        joinRoomReqDto.setRoomId(roomId);
        JoinRoomRespDto joinRoomRespDto = partyService.파티방참가(joinRoomReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티방 참가 완료", joinRoomRespDto), HttpStatus.CREATED);
    }

    // 파티방 나가기 (본인)
    @AuthorizationCheck
    @PutMapping("/party/user/{userId}/exit/{roomId}")
    public ResponseEntity<?> exitRoom(@PathVariable Long userId, @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티방 나가기 컨트롤러 호출");
        ExitRoomRespDto exitRoomRespDto = partyService.파티방나가기(userId, roomId);
        return new ResponseEntity<>(new ResponseDto<>("파티방 나가기 완료", exitRoomRespDto), HttpStatus.OK);
    }

    // 파티방 종료(방장)
    @AuthorizationCheck
    @PutMapping("/party/user/{userId}/end/{roomId}")
    public ResponseEntity<?> endRoom(@PathVariable Long userId, @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티방 종료 컨트롤러 호출");
        EndRoomRespDto endRoomRespDto = partyService.파티방종료(userId, roomId);
        return new ResponseEntity<>(new ResponseDto<>("파티방 종료 완료", endRoomRespDto), HttpStatus.OK);
    }

    // 파티원 추방(방장)
    @AuthorizationCheck
    @PutMapping("/party/user/{userId}/kick/{roomId}")
    public ResponseEntity<?> kickUser(@RequestBody KickUserReqDto kickUserReqDto, @PathVariable Long userId,
            @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        log.debug("디버그 : 파티원 추방 컨트롤러 호출");
        kickUserReqDto.setUserId(userId);
        kickUserReqDto.setRoomId(roomId);
        KickUserRespDto kickUserRespDto = partyService.파티원추방(kickUserReqDto);
        return new ResponseEntity<>(new ResponseDto<>("파티원 추방 완료", kickUserRespDto), HttpStatus.OK);
    }

    // 나의 모집 파티 목록
    @AuthorizationCheck
    @GetMapping("/party/user/{userId}/myrooms")
    public ResponseEntity<?> findByMyIdRoom(@PathVariable Long userId, @AuthenticationPrincipal LoginUser loginUser) {
        RoomListByMyIdRespDto roomListByMyIdRespDto = partyService.나의모집파티목록(userId);
        return new ResponseEntity<>(new ResponseDto<>("나의 모집 파티 목록 보기 완료", roomListByMyIdRespDto), HttpStatus.OK);
    }

    // 파티방 상세보기
    @AuthorizationCheck
    @GetMapping("/party/user/{userId}/detail/{roomId}")
    public ResponseEntity<?> detailRoom(@PathVariable Long userId, @PathVariable Long roomId,
            @AuthenticationPrincipal LoginUser loginUser) {
        DetailRoomRespDto detailRoomRespDto = partyService.파티방상세보기(userId, roomId);
        return new ResponseEntity<>(new ResponseDto<>("파티방 상세보기 완료", detailRoomRespDto), HttpStatus.OK);
    }

    // 전체 파티방 목록 보기 -> 카테고리별, 페이징, 검색
    @AuthorizationCheck
    @GetMapping("/party/user/{userId}/list")
    public ResponseEntity<?> findAllRoom(@PathVariable Long userId, @AuthenticationPrincipal LoginUser loginUser,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "gameCodeId", defaultValue = "") Long gameCodeId,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        RoomListRespDto roomListRespDto = partyService.전체파티방목록보기(gameCodeId, keyword, page);
        return new ResponseEntity<>(new ResponseDto<>("전체 파티방 목록 보기 완료", roomListRespDto), HttpStatus.OK);
    }

    // 참가중인 파티방 목록 보기
    @AuthorizationCheck
    @GetMapping("/party/user/{userId}/joins")
    public ResponseEntity<?> findJoinRooms(@PathVariable Long userId, @AuthenticationPrincipal LoginUser loginUser) {

        RoomListByIdRespDto roomListByIdRespDto = partyService.참가중인파티방목록보기(userId);
        return new ResponseEntity<>(new ResponseDto<>("참가중인 파티방 목록 보기 완료",
                roomListByIdRespDto), HttpStatus.OK);
    }
}
