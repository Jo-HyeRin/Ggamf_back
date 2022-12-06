package shop.ggamf.ggamf.service;

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
import shop.ggamf.ggamf.dto.PartyRespDto.CreateRoomRespDto;
import shop.ggamf.ggamf.dto.PartyRespDto.JoinRoomRespDto;

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
}
