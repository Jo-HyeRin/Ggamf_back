package shop.ggamf.ggamf.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Setter
    @Getter
    public static class EndRoomRespDto {
        private RoomDto room;
        private List<EnterDto> enters = new ArrayList<>();

        public EndRoomRespDto(Room room, List<Enter> enters) {
            this.room = new RoomDto(room);
            this.enters = enters.stream().map((enter) -> new EnterDto(enter))
                    .collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class RoomDto {
            private Long id;
            private String gameName;
            private String roomName;
            private Boolean active;

            public RoomDto(Room room) {
                this.id = room.getId();
                this.gameName = room.getGameName();
                this.roomName = room.getRoomName();
                this.active = room.getActive();
            }
        }

        @Setter
        @Getter
        public class EnterDto {
            private Long id;
            private Long userId;
            private Long roomId;
            private Boolean stay;

            public EnterDto(Enter enter) {
                this.id = enter.getId();
                this.userId = enter.getUser().getId();
                this.roomId = enter.getRoom().getId();
                this.stay = enter.getStay();
            }
        }
    }

    @Setter
    @Getter
    public static class KickUserRespDto {
        private Long id;
        private Long kickUserId;
        private String kickUsername;
        private String kickName;
        private Long roomId;
        private Boolean stay;

        public KickUserRespDto(Enter enter) {
            this.id = enter.getId();
            this.kickUserId = enter.getUser().getId();
            this.kickUsername = enter.getUser().getUsername();
            this.kickName = enter.getUser().getName();
            this.roomId = enter.getRoom().getId();
            this.stay = enter.getStay();
        }
    }
}
