package entity.room.builder;

public class LaboratoryBuilder extends RoomBuilder {

    @Override
    public void setRoomType() {
        this.getRoom().setType("Laboratory");
    }
}
