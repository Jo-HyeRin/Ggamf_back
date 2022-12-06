package shop.ggamf.ggamf.dto;

import lombok.Getter;
import lombok.Setter;
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

}
