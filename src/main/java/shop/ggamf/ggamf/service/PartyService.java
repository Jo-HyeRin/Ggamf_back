package shop.ggamf.ggamf.service;

import java.util.ArrayList;
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
        // 현재 생성한 방이 3개 초과 시 throw
        List<Room> roomListPS = roomRepository.findByUserId(createRoomReqDto.getUserId());
        if (roomListPS.size() > 2) {
            throw new CustomApiException("방은 인당 최대 3개 운영 가능합니다", HttpStatus.BAD_REQUEST);
        }
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
        if (roomPS.getActive() == false) {
            throw new CustomApiException("이미 종료되어 참가할 수 없는 방입니다", HttpStatus.BAD_REQUEST);
        }
        if (roomPS.getUser().getId() == joinRoomReqDto.getUserId()) {
            throw new CustomApiException("당신이 방장인 방입니다 참가할 수 없습니다", HttpStatus.BAD_REQUEST);
        }
        // 현재 이 방에 참가 중이면 throw
        List<Enter> enterList = enterRepository.findByUserId(joinRoomReqDto.getUserId());
        List<Long> enterRoomIdList = new ArrayList<>();
        for (int i = 0; i < enterList.size(); i++) {
            enterRoomIdList.add(enterList.get(i).getRoom().getId());
        }
        if (enterRoomIdList.contains(joinRoomReqDto.getRoomId())) {
            throw new CustomApiException("이미 참가 중인 방입니다", HttpStatus.BAD_REQUEST);
        }

        // 실행
        Enter enter = joinRoomReqDto.toEntity(userPS, roomPS);
        Enter enterPS = enterRepository.save(enter);
        // 응답
        return new JoinRoomRespDto(enterPS);
    }

    @Transactional
    public ExitRoomRespDto 파티방나가기(Long userId, Long roomId) { // 나의 enter.stay = false 변경하기
        log.debug("디버그 : 파티방 나가기 서비스 호출");
        // 검증
        Enter enterPS = enterRepository.findByRoomIdAndUserId(roomId, userId)
                .orElseThrow(
                        () -> new CustomApiException("당신이 나갈 수 없는 방입니다", HttpStatus.FORBIDDEN));
        if (enterPS.getRoom().getActive() == false) {
            throw new CustomApiException("이미 종료된 방입니다", HttpStatus.BAD_REQUEST);
        }
        if (enterPS.getStay() == false) {
            throw new CustomApiException("이미 방에서 나온 상태입니다", HttpStatus.BAD_REQUEST);
        }
        if (enterPS.getRoom().getUser().getId() == userId) {
            throw new CustomApiException("당신이 방장입니다 나갈 수 없습니다 방을 종료하세요", HttpStatus.BAD_REQUEST);
        }
        // 실행
        enterPS.notStayRoom();
        // 응답
        return new ExitRoomRespDto(enterPS);
    }

    @Transactional
    public EndRoomRespDto 파티방종료(Long userId, Long roomId) {
        log.debug("디버그 : 파티방 종료 서비스 호출");
        // Room active = false로 변경
        // Enter roomId = endRoomReqDto.getRoomId(), stay=true 찾아서 stay = false 변경
        // 방 존재 여부 확인
        Room roomPS = roomRepository.findById(roomId)
                .orElseThrow(
                        () -> new CustomApiException("해당 방을 찾을 수 없습니다", HttpStatus.FORBIDDEN));
        // 방장 유무 확인
        if (userId != roomPS.getUser().getId()) {
            throw new CustomApiException("당신은 방장이 아닙니다", HttpStatus.BAD_REQUEST);
        }
        // 활성화 유무 확인
        if (roomPS.getActive() == false) {
            throw new CustomApiException("이미 종료된 방입니다", HttpStatus.BAD_REQUEST);
        }
        // 실행
        roomPS.endRoom();

        // 방에 끝까지 남은 유저 확인
        List<Enter> enterListPS = enterRepository.findByRoomId(roomId);
        // stay = false 변경
        List<Enter> enterList = enterListPS.stream()
                .map((enter) -> new Enter(enter.getId(), enter.getUser(),
                        enter.getRoom(), false, true))
                .collect(Collectors.toList());
        // 실행
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
        if (enterPS.getRoom().getActive() == false) {
            throw new CustomApiException("이미 종료된 방입니다", HttpStatus.BAD_REQUEST);
        }
        if (enterPS.getRoom().getUser().getId() != kickUserReqDto.getUserId()) {
            throw new CustomApiException("당신은 방장이 아닙니다. 추방 권한이 없습니다", HttpStatus.BAD_REQUEST);
        }
        if (enterPS.getStay() == false) {
            throw new CustomApiException("이 방에 존재하는 유저가 아닙니다", HttpStatus.BAD_REQUEST);
        }
        enterPS.notStayRoom();
        return new KickUserRespDto(enterPS);
    }

    public RoomListByMyIdRespDto 나의모집파티목록(Long userId) {
        List<Room> roomListPS = roomRepository.findByUserId(userId);
        return new RoomListByMyIdRespDto(roomListPS);
    }

    public DetailRoomRespDto 파티방상세보기(Long userId, Long roomId) {
        Room roomPS = roomRepository.findById(roomId)
                .orElseThrow(
                        () -> new CustomApiException("존재하지 않는 파티방입니다", HttpStatus.FORBIDDEN));
        if (roomPS.getActive() == false) {
            throw new CustomApiException("이미 종료된 방입니다.", HttpStatus.BAD_REQUEST);
        }
        List<Enter> enterListPS = enterRepository.findByUserId(userId);
        List<Long> enterRoomIdList = new ArrayList<>();
        for (int i = 0; i < enterListPS.size(); i++) {
            enterRoomIdList.add(enterListPS.get(i).getRoom().getId());
        }
        // 내가 방장이거나 입장 중인가
        if (roomPS.getUser().getId() == userId || enterRoomIdList.contains(roomId)) {
            return new DetailRoomRespDto(roomPS);
        } else {
            throw new CustomApiException("당신은 이 방의 방장도, 이 방 입장 유저도 아닙니다", HttpStatus.BAD_REQUEST);
        }
    }

    public RoomListRespDto 전체파티방목록보기(Long gameCodeId, String keyword, Integer page) {
        // 게임 코드가 존재하는 지 확인
        if (gameCodeId != null) {
            GameCode gameCodePS = gameCodeRepository.findById(gameCodeId)
                    .orElseThrow(
                            () -> new CustomApiException("존재하지 않는 게임코드입니다", HttpStatus.FORBIDDEN));
        }
        List<Room> roomListPS = roomRepository.findAll(gameCodeId, keyword, page);
        // 목록보다 더 큰 페이지 요청 시
        if (roomListPS.size() < page * 10) {
            throw new CustomApiException("이 페이지에는 파티방 목록이 없습니다", HttpStatus.BAD_REQUEST);
        }
        return new RoomListRespDto(roomListPS);
    }

    public RoomListByIdRespDto 참가중인파티방목록보기(Long userId) {
        // Enter.userId == loginUser.id
        List<Enter> enterListPS = enterRepository.findByUserId(userId);

        return new RoomListByIdRespDto(enterListPS);
    }

}
