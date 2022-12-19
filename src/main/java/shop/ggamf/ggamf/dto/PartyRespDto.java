package shop.ggamf.ggamf.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.ggamf.ggamf.domain.enter.Enter;
import shop.ggamf.ggamf.domain.room.PeopleDto;
import shop.ggamf.ggamf.domain.room.Room;

public class PartyRespDto {

    @Getter
    @Setter
    public static class CreateRoomRespDto {
        private Long id;
        private Long userId;
        private String uid;
        private Long gameCodeId;
        private String gameName;
        private String roomName;
        private Long totalPeople;

        public CreateRoomRespDto(Room room) {
            this.id = room.getId();
            this.userId = room.getUser().getId();
            this.uid = room.getUser().getUid();
            this.gameCodeId = room.getGameCode().getId();
            this.gameName = room.getGameName();
            this.roomName = room.getRoomName();
            this.totalPeople = room.getTotalPeople();
        }
    }

    @Setter
    @Getter
    public static class JoinRoomRespDto {
        private Long roomId;
        private String roomName;
        private String userNick;
        private String uid;

        public JoinRoomRespDto(Enter enter) {
            this.roomId = enter.getRoom().getId();
            this.roomName = enter.getRoom().getRoomName();
            this.userNick = enter.getUser().getNickname();
            this.uid = enter.getUser().getUid();
        }
    }

    @Setter
    @Getter
    public static class ExitRoomRespDto {
        private Long id;
        private Long userId;
        private String userNick;
        private String uid;
        private Long roomId;
        private String roomName;

        public ExitRoomRespDto(Enter enter) {
            this.id = enter.getId();
            this.userId = enter.getUser().getId();
            this.userNick = enter.getUser().getNickname();
            this.uid = enter.getUser().getUid();
            this.roomId = enter.getRoom().getId();
            this.roomName = enter.getRoom().getRoomName();
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

            public RoomDto(Room room) {
                this.id = room.getId();
                this.gameName = room.getGameName();
                this.roomName = room.getRoomName();
            }
        }

        @Setter
        @Getter
        public class EnterDto {
            private Long id;
            private Long userId;
            private String userNick;
            private String uid;
            private Long roomId;
            private String roomName;

            public EnterDto(Enter enter) {
                this.id = enter.getId();
                this.userId = enter.getUser().getId();
                this.userNick = enter.getUser().getNickname();
                this.uid = enter.getUser().getUid();
                this.roomId = enter.getRoom().getId();
                this.roomName = enter.getRoom().getRoomName();
            }
        }
    }

    @Setter
    @Getter
    public static class KickUserRespDto {
        private Long userId;
        private String userNick;
        private String uid;
        private Long roomId;

        public KickUserRespDto(Enter enter) {
            this.userId = enter.getUser().getId();
            this.userNick = enter.getUser().getNickname();
            this.uid = enter.getUser().getUid();
            this.roomId = enter.getRoom().getId();
        }
    }

    @Setter
    @Getter
    public static class DetailRoomRespDto {

        private RoomDto room;
        List<EnterDto> enters = new ArrayList<>();

        public DetailRoomRespDto(Room room, List<Enter> enters) {
            this.room = new RoomDto(room);
            this.enters = enters.stream().map((enter) -> new EnterDto(enter)).collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class RoomDto {
            private Long roomId;
            private String roomName;
            private String roomOwnerNickName;
            private String uid;
            private String gameName;

            public RoomDto(Room room) {
                this.roomId = room.getId();
                this.roomName = room.getRoomName();
                this.roomOwnerNickName = room.getUser().getNickname();
                this.uid = room.getUser().getUid();
                this.gameName = room.getGameName();
            }
        }

        @Setter
        @Getter
        public class EnterDto {
            private Long userId;
            private String userNick;
            private String uid;

            public EnterDto(Enter enter) {
                this.userId = enter.getUser().getId();
                this.userNick = enter.getUser().getNickname();
                this.uid = enter.getUser().getUid();
            }
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class AllRoomDto { // 전체 파티방 목록 보기
        private Long id;
        private String roomName;
        private String nickName;
        private String uid;
        private Long totalPeople;
        private BigInteger count;
        private String gameName;
        private String gameLogo;

        public AllRoomDto setAllRoom(Room room, PeopleDto peopleDto) {
            AllRoomDto roomDto = new AllRoomDto();
            roomDto.id = room.getId();
            roomDto.roomName = room.getRoomName();
            roomDto.nickName = room.getUser().getNickname();
            roomDto.uid = room.getUser().getUid();
            roomDto.totalPeople = room.getTotalPeople();
            roomDto.count = peopleDto.getCount();
            roomDto.gameName = room.getGameName();
            roomDto.gameLogo = room.getGameCode().getLogo();
            return roomDto;
        }

        public AllRoomDto(Room room, PeopleDto peopleDto) {
            this.id = room.getId();
            this.roomName = room.getRoomName();
            this.nickName = room.getUser().getNickname();
            this.uid = room.getUser().getUid();
            this.totalPeople = room.getTotalPeople();
            this.count = peopleDto.getCount();
            this.gameName = room.getGameName();
            this.gameLogo = room.getGameCode().getLogo();
        }
    }

    @Setter
    @Getter
    public static class RoomListByIdRespDto { // 내가 참여중인 방

        List<RoomDto> rooms;

        public RoomListByIdRespDto(List<Enter> rooms) {
            this.rooms = rooms.stream().map((enter) -> new RoomDto(enter)).collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class RoomDto {
            private Long id;
            private String roomName;
            private String nickName;
            private String uid;
            private Long totalPeople;
            private String gameName;
            private String gameLogo;

            public RoomDto(Enter enter) {
                this.id = enter.getRoom().getId();
                this.roomName = enter.getRoom().getRoomName();
                this.nickName = enter.getRoom().getUser().getNickname();
                this.uid = enter.getRoom().getUser().getUid();
                this.totalPeople = enter.getRoom().getTotalPeople();
                this.gameName = enter.getRoom().getGameName();
                this.gameLogo = enter.getRoom().getGameCode().getLogo();
            }
        }
    }

    @Setter
    @Getter
    public static class RoomListByMyIdRespDto { // 내가방장인파티방목록보기

        List<RoomDto> rooms;

        public RoomListByMyIdRespDto(List<Room> rooms) {
            this.rooms = rooms.stream().map((room) -> new RoomDto(room)).collect(Collectors.toList());
        }

        @Setter
        @Getter
        public class RoomDto {
            private Long id;
            private String roomName;
            private String nickName;
            private String uid;
            private Long totalPeople;
            private String gameName;
            private String gameLogo;

            public RoomDto(Room room) {
                this.id = room.getId();
                this.roomName = room.getRoomName();
                this.nickName = room.getUser().getNickname();
                this.uid = room.getUser().getUid();
                this.totalPeople = room.getTotalPeople();
                this.gameName = room.getGameName();
                this.gameLogo = room.getGameCode().getLogo();
            }
        }
    }
}
