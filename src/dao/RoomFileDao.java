package dao;

import entity.room.Room;

import java.io.*;
import java.util.Vector;

public class RoomFileDao {

    private static final String FILE_PATH = "room.ser";

    /**
     * This method is used to serialize an object vector in a file
     *
     * @param vec - Vector
     */
    private void serialization(Vector<Room> vec) {
        FileOutputStream fileOutput = null;
        ObjectOutputStream objectOutput = null;
        try {
            //Open file stream
            fileOutput = new FileOutputStream(FILE_PATH);
            //Open object stream
            objectOutput = new ObjectOutputStream(fileOutput);
            //Write object on file
            objectOutput.writeObject(vec);
            System.out.println("RoomFileDao serialization: object has been written!");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("RoomFileDao serialization: object has NOT been written!");
        } finally {
            try {
                if (objectOutput != null) {
                    //Close object stream
                    objectOutput.close();
                }
                if (fileOutput != null) {
                    //Close file stream
                    fileOutput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is used to deserialize an object vector from a file
     *
     * @return Vector
     */
    private Vector<Room> deserialization() {
        Vector<Room> vec = null;
        try {
            //Open file stream
            FileInputStream fileOutput = new FileInputStream(FILE_PATH);
            //Open object stream
            ObjectInputStream objectOutput = new ObjectInputStream(fileOutput);
            try {
                //Get vector object
                vec = (Vector<Room>) objectOutput.readObject();
                System.out.println("RoomFileDao deserialization: read object " + vec);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("RoomFileDao deserialization: couldn't read object");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("RoomFileDao deserialization: return object " + vec);
        return vec;
    }

    /**
     * This method is used to ensure that a room is present in a file
     *
     * @param name - String
     * @return
     */
    public Boolean isRoomPresent(String name) {
        Vector<Room> vec = deserialization();
        for (Room room : vec) {
            if (room.getName().equals(name)) {
                System.out.println("RoomFileDao isRoomPresent(): yay the room you're looking for is present!");
                return true;
            }
        }
        System.out.println("RoomFileDao isRoomPresent(): the room you're looking for is NOT present!");
        return false;
    }

    /**
     * This method is used to add a room to a file
     *
     * @param room - String
     */
    public void addRoom(Room room) {
        if (!isRoomPresent(room.getName())) {
            Vector<Room> vec = deserialization();
            vec.add(room);
            this.serialization(vec);
            System.out.println("RoomFileDao addRoom(): yay your room has been added to the file!");
        }
    }

    /**
     * This method is used to delete a room from a file
     *
     * @param room - Room
     */
    public void deleteRoom(Room room) {
        if (isRoomPresent(room.getName())) {
            Vector<Room> vec = deserialization();
            for (int i = 0; i < vec.size(); i++) {
                if (vec.get(i).getName().equals(room.getName())) {
                    vec.remove(i);
                    this.serialization(vec);
                    System.out.println("RoomFileDao deleteRoom: yay the room you selected has been deleted succesfully!");
                    return;
                }
            }
        }
    }

    /**
     * This method is used to update an existing room in the file
     *
     * @param room - Room
     */
    public void updateRoom(Room room) {
        if (isRoomPresent(room.getName())) {
            Vector<Room> vec = deserialization();
            for (Room currentRoom : vec) {
                if (currentRoom.getName().equals(room.getName())) {
                    currentRoom.setType(room.getType());
                    currentRoom.setBuilding(room.getBuilding());
                    currentRoom.setSeats(room.getSeats());
                    currentRoom.setBoard(room.getBoard());
                    currentRoom.setProjectors(room.getProjectors());
                    currentRoom.setComputers(room.getComputers());
                    currentRoom.setTeacherDesk(room.hasTeacherDesk());
                    this.serialization(vec);
                    System.out.println("RoomFileDao updateRoom(): yay the room has been successfully updated!");
                    return;
                }
            }
        }
    }

    /**
     * This methof is used to get an existing room in a file
     *
     * @param name - String
     * @return
     */
    public Room getRoom(String name) {
        Room room;
        Vector<Room> vec = deserialization();
        for (Room currentRoom : vec) {
            if (currentRoom.getName().equals(name)) {
                room = currentRoom;
                System.out.println("RoomFileDao getRoom(): " + room.getName() + room.getType() + room.getBuilding());
                return room;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Room room = new Room("B45", "CongressHall", 3);
        Vector<Room> vector = new Vector<Room>();
        vector.add(room);
        RoomFileDao roomFileDao = new RoomFileDao();
        //roomFileDao.serialization(vector);
        //roomFileDao.deserialization();
        //roomFileDao.isRoomPresent("B45");
        //roomFileDao.addRoom(room);
        //roomFileDao.deleteRoom(room);
        //roomFileDao.updateRoom(room);
        //roomFileDao.getRoom("B45");
    }
}
