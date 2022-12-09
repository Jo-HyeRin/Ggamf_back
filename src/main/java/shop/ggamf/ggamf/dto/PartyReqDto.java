package shop.ggamf.ggamf.dto;

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
        private String roomName;
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
    public static class ExitRoomReqDto {
        private Long userId;
        private Long roomId;
    }

    @Setter
    @Getter
    public static class EndRoomReqDto {
        private Long userId;
        private Long roomId;
    }

    @Setter
    @Getter
    public static class KickUserReqDto {
        private Long userId;
        private Long roomId;
        private Long kickUserId; // 추방대상유저
    }
}
