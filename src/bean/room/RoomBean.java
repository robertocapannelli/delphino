package bean.room;

import control.HandleRoom;
import entity.Building;
import entity.room.Room;

public class RoomBean {

    private Room myRoom;
    private int id;
    private String name;
    private Building building;
    private String type;
    private int seats;
    private String board;
    private int projectors;
    private int computers;
    private Boolean teacherDesk;
    private String area;

    public Room getMyRoom() {
        return myRoom;
    }

    public void setMyRoom(int id) {
        HandleRoom handleRoom = new HandleRoom();
        this.myRoom = handleRoom.getRoomById(id);
    }

    public Boolean getTeacherDesk() {
        return teacherDesk;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = myRoom.getBuilding().getArea();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public int getProjectors() {
        return projectors;
    }

    public void setProjectors(int projectors) {
        this.projectors = projectors;
    }

    public int getComputers() {
        return computers;
    }

    public void setComputers(int computers) {
        this.computers = computers;
    }

    public Boolean hasTeacherDesk() {
        return teacherDesk;
    }

    public void setTeacherDesk(Boolean teacherDesk) {
        this.teacherDesk = teacherDesk;
    }

}
