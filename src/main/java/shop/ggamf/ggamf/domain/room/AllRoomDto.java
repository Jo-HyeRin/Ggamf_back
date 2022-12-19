package shop.ggamf.ggamf.domain.room;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AllRoomDto {
    private Long id;
    private String roomName;
    private String nickName;
    private String uid;
    private Long totalPeople;
    private BigInteger count;
    private String gameName;
    private String gameLogo;

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

    public AllRoomDto allRoomDto(Room room, PeopleDto peopleDto) {
        AllRoomDto allRoomDto = new AllRoomDto(room, peopleDto);
        allRoomDto.id = room.getId();
        allRoomDto.roomName = room.getRoomName();
        allRoomDto.nickName = room.getUser().getNickname();
        allRoomDto.uid = room.getUser().getUid();
        allRoomDto.totalPeople = room.getTotalPeople();
        allRoomDto.count = peopleDto.getCount();
        allRoomDto.gameName = room.getGameName();
        allRoomDto.gameLogo = room.getGameCode().getLogo();
        return allRoomDto;
    }

}