package controller;

import dao.RoomDao;
import entity.room.Room;
import entity.room.builder.RoomBuilder;
import entity.room.builder.RoomDirectorBuilder;

import java.util.Vector;

public class RoomHandlerController {

    private RoomDao rd = RoomDao.getInstance();

    public void addNewRoom(String name, String type, int building, String board, boolean teacherDesk, int seats, int projectors, int computers ){
        RoomDirectorBuilder director = new RoomDirectorBuilder();
        RoomBuilder builder = director.buildRoom(name, type, building)
                .setBoard(board)
                .setTeacherDesk(teacherDesk)
                .setSeats(seats)
                .setProjectors(projectors)
                .setComputers(computers);
        Room room = builder.getRoom();
        rd.addRoom(room);
    }

    public void deleteRoom(Room room){
        rd.deleteRoom(room);
    }

    public void updateRoom(Room room){
        rd.updateRoom(room);
    }

    public Room getRoom(Room room){
        return rd.getRoom(room);
    }

    public Vector<Room> getAllRooms(){
        return rd.getAllRooms();
    }

    /*public static void main(String[] args) {
        RoomHandlerController rhc = new RoomHandlerController();
        rhc.addNewRoom("c12", "ClassRoom", 5, "nera", true, 23, 2, 23);
    }*/
}