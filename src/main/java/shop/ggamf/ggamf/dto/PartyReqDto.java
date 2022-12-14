package shop.ggamf.ggamf.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.gameCode.GameCode;
import shop.ggamf.ggamf.domain.room.Room;
import shop.ggamf.ggamf.domain.user.User;

public class PartyReqDto {

    @Setter
    @Getter
    public static class CreateRoomReqDto {
        private String gameName;

        @NotEmpty(message = "파티방 이름은 필수 입력 값입니다.")
        private String roomName;

        @NotEmpty(message = "인원은 필수 입력 값입니다.")
        private Long totalPeople;

        private Long userId;
        private Long gameCodeId;

        public Room toEntity(User user, GameCode gameCode) {
            return Room.builder()
                    .gameName(gameName)
                    .roomName(roomName)
                    .totalPeople(totalPeople)
                    .active(true)
                    .user(user)
                    .gameCode(gameCode)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class JoinRoomReqDto {
        private Long userId;
        private Long roomId;

        public Enter toEntity(User user, Room room) {
            return Enter.builder()
                    .user(user)
                    .room(room)
                    .stay(true)
                    .stayUntilEnd(false)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class KickUserReqDto {
        private Long userId;
        private Long roomId;

        @NotEmpty(message = "추방할 유저는 필수 입력 값입니다.")
        private Long kickUserId; // 추방대상유저
    }
}
