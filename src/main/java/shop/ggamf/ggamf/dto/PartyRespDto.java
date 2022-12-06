package shop.ggamf.ggamf.dto;

import lombok.Getter;
import lombok.Setter;

import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.room.Room;

public class PartyRespDto {

    @Getter
    @Setter
    public static class CreateRoomRespDto {
        private Long id;
        private Long userId;
        private Long gameCodeId;
        private String gameName;
        private String roomName;
        private Long totalPeople;

        public CreateRoomRespDto(Room room) {
            this.id = room.getId();
            this.userId = room.getUser().getId();
            this.gameCodeId = room.getGameCode().getId();
            this.gameName = room.getGameName();
            this.roomName = room.getRoomName();
            this.totalPeople = room.getTotalPeople();
        }
    }

    @Setter
    @Getter
    public static class JoinRoomRespDto {
        private Long id;
        private Long userId;
        private Long roomId;
        // 나중에 채팅방 보여줘야함

        public JoinRoomRespDto(Enter enter) {
            this.id = enter.getId();
            this.userId = enter.getUser().getId();
            this.roomId = enter.getRoom().getId();
        }
    }

    @Setter
    @Getter
    public static class ExitRoomRespDto {
        private Long id;
        private Long userId;
        private Long roomId;
        private Boolean stay;
        // 나중에 추천친구목록 보여줘야함

        public ExitRoomRespDto(Enter enter) {
            this.id = enter.getId();
            this.userId = enter.getUser().getId();
            this.roomId = enter.getRoom().getId();
            this.stay = enter.getStay();
        }
    }
}
