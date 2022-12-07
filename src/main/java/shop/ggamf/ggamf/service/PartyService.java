package shop.ggamf.ggamf.service;

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
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.gameCode.GameCodeRepository;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.room.RoomRepository;
import shop.ggamf.ggamf.domain.user.User;
import shop.ggamf.ggamf.domain.user.UserRepository;
import shop.ggamf.ggamf.dto.PartyReqDto.CreateRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.EndRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.ExitRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.JoinRoomReqDto;
import shop.ggamf.ggamf.dto.PartyReqDto.KickUserReqDto;
import shop.ggamf.ggamf.dto.PartyRespDto.CreateRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.EndRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.ExitRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.JoinRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.KickUserRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.RoomListByMyIdRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PartyService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final GameCodeRepository gameCodeRepository;
    private final EnterRepository enterRepository;

    @Transactional
    public CreateRoomRespDto 파티방생성(CreateRoomReqDto createRoomReqDto) {
        log.debug("디버그 : 파티방생성 서비스 호출");
        // 검증
        User userPS = userRepository.findById(createRoomReqDto.getUserId())
                .orElseThrow(
                        () -> new CustomApiException("해당 유저가 없습니다", HttpStatus.FORBIDDEN));
        GameCode gameCodePS = gameCodeRepository.findById(createRoomReqDto.getGameCodeId())
                .orElseThrow(
                        () -> new CustomApiException("게임 카테고리에 존재하지 않습니다", HttpStatus.FORBIDDEN));

        // 기타(1번)가 아닐 때 카테고리 게임 이름 입력해주기
        if (createRoomReqDto.getGameCodeId() != 1L) {
            createRoomReqDto.setGameName(gameCodePS.getGameName());
        }
        // 실행
        Room room = createRoomReqDto.toEntity(userPS, gameCodePS);
        Room roomPS = roomRepository.save(room);
        // 응답
        return new CreateRoomRespDto(roomPS);
    }

    @Transactional
    public JoinRoomRespDto 파티방참가(JoinRoomReqDto joinRoomReqDto) {
        log.debug("디버그 : 파티방 참가 서비스 호출");
        // 검증
        User userPS = userRepository.findById(joinRoomReqDto.getUserId())
                .orElseThrow(
                        () -> new CustomApiException("해당 유저가 없습니다", HttpStatus.FORBIDDEN));
        Room roomPS = roomRepository.findById(joinRoomReqDto.getRoomId())
                .orElseThrow(
                        () -> new CustomApiException("해당 파티방이 없습니다", HttpStatus.FORBIDDEN));
        // 실행
        Enter enter = joinRoomReqDto.toEntity(userPS, roomPS);
        Enter enterPS = enterRepository.save(enter);
        // 응답
        return new JoinRoomRespDto(enterPS);
    }

    @Transactional
    public ExitRoomRespDto 파티방나가기(ExitRoomReqDto exitRoomReqDto) { // 나의 enter.stay = false 변경하기
        log.debug("디버그 : 파티방 나가기 서비스 호출");
        // 검증
        Enter enterPS = enterRepository.findByRoomIdAndUserId(exitRoomReqDto.getRoomId(), exitRoomReqDto.getUserId())
                .orElseThrow(
                        () -> new CustomApiException("나갈 수 없는 방입니다", HttpStatus.FORBIDDEN));
        if (enterPS.getStay() == false) {
            throw new CustomApiException("이미 종료된 방입니다", HttpStatus.BAD_REQUEST);
        }
        if (enterPS.getRoom().getUser().getId() == exitRoomReqDto.getUserId()) {
            throw new CustomApiException("당신이 방장입니다", HttpStatus.BAD_REQUEST);
        }
        // 실행
        enterPS.notStayRoom();
        // 응답
        return new ExitRoomRespDto(enterPS);
    }

    @Transactional
    public EndRoomRespDto 파티방종료(EndRoomReqDto endRoomReqDto) {
        log.debug("디버그 : 파티방 종료 서비스 호출");
        // Room active = false로 변경
        Room roomPS = roomRepository.findById(endRoomReqDto.getRoomId())
                .orElseThrow(
                        () -> new CustomApiException("해당 방을 찾을 수 없습니다", HttpStatus.FORBIDDEN));
        if (endRoomReqDto.getUserId() != roomPS.getUser().getId()) {
            throw new CustomApiException("당신은 방장이 아닙니다", HttpStatus.BAD_REQUEST);
        }
        if (roomPS.getActive() == false) {
            throw new CustomApiException("이미 종료된 방입니다", HttpStatus.BAD_REQUEST);
        }
        roomPS.endRoom();

        // Enter roomId = endRoomReqDto.getRoomId() 찾아서 stay = false 변경
        List<Enter> enterListPS = enterRepository.findByRoomId(endRoomReqDto.getRoomId());

        List<Enter> enterList = enterListPS.stream()
                .map((enter) -> new Enter(enter.getId(), enter.getUser(),
                        enter.getRoom(), false))
                .collect(Collectors.toList());
        List<Enter> enterListUpdate = enterRepository.saveAll(enterList);

        // 응답
        return new EndRoomRespDto(roomPS, enterListUpdate);
    }

    @Transactional
    public KickUserRespDto 파티원추방(KickUserReqDto kickUserReqDto) {
        log.debug("디버그 : 파티원 추방 서비스 호출");
        Enter enterPS = enterRepository
                .findByRoomIdAndUserId(kickUserReqDto.getRoomId(), kickUserReqDto.getKickUserId())
                .orElseThrow(
                        () -> new CustomApiException("해당 파티원은 추방할 수 없습니다", HttpStatus.FORBIDDEN));
        enterPS.notStayRoom();
        return new KickUserRespDto(enterPS);
    }

    public RoomListByMyIdRespDto 나의모집파티목록(Long userId) {
        List<Room> roomListPS = roomRepository.findByUserId(userId);
        return new RoomListByMyIdRespDto(roomListPS);
    }

}
