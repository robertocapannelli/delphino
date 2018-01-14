package dao.room;

import dao.AbstractDao;
import dao.DataSource;
import entity.room.Room;
import entity.room.builder.RoomBuilder;
import entity.room.builder.RoomDirectorBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class RoomDaoDb extends AbstractDao implements RoomDao {

    private static final String TABLE_NAME = "room";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_BUILDING = "building";
    private static final String COLUMN_BOARD = "board";
    private static final String COLUMN_TEACHER_DESK = "teacher_desk";
    private static final String COLUMN_SEATS = "seats";
    private static final String COLUMN_PROJECTORS = "projectors";
    private static final String COLUMN_COMPUTERS = "computers";

    private DataSource ds = new DataSource();

    /**
     * This method checks if a room is present
     * in the database using the room's id as parameter
     *
     * @param name - String
     * @return Boolean
     */
    private Boolean isRoomPresent(String name) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT from ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(COLUMN_NAME)
                .append(" = '")
                .append(name).append("'");
        return this.isPresent(sql.toString());
    }

    /**
     * This method is used to add a new room to the database
     *
     * @param room - Room
     * @throws SQLException
     */
    @Override
    public void addRoom(Room room){
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(TABLE_NAME).append("(")
                .append(COLUMN_NAME).append(", ")
                .append(COLUMN_TYPE).append(", ")
                .append(COLUMN_SEATS).append(", ")
                .append(COLUMN_BOARD).append(", ")
                .append(COLUMN_PROJECTORS).append(", ")
                .append(COLUMN_COMPUTERS).append(", ")
                .append(COLUMN_TEACHER_DESK).append(", ")
                .append(COLUMN_BUILDING).append(")")
                .append(" values(")
                .append("'").append(room.getName()).append("', ")
                .append("'").append(room.getType()).append("', ")
                .append("'").append(room.getSeats()).append("', ")
                .append("'").append(room.getBoard()).append("', ")
                .append("'").append(room.getProjectors()).append("', ")
                .append("'").append(room.getComputers()).append("', ")
                .append("'").append(room.hasTeacherDesk()).append("', ")
                .append("'").append(room.getBuilding()).append("')");
        this.executeUpdate(sql.toString());
    }

    /**
     * This method is used to delete {@code room} object
     * from database
     *
     * @param room - Room
     * @throws NullPointerException
     */
    @Override
    public void deleteRoom(Room room) throws NullPointerException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE from ")
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append(COLUMN_ID).append(" = '").append(room.getId()).append("'");
        this.executeUpdate(sql.toString());
    }

    //TODO This should be improved I think isn't working very well. An option could be split each update in a method

    /**
     * This method is used to update a room
     *
     * @param room - Room
     * @throws NullPointerException
     */
    @Override
    public void updateRoom(Room room) throws NullPointerException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE " + TABLE_NAME)
                .append(" SET ")
                .append(COLUMN_NAME).append(" = '").append(room.getName().toUpperCase()).append("', ")
                .append(COLUMN_TYPE).append(" = '").append(room.getType()).append("', ")
                .append(COLUMN_BUILDING).append(" = '").append(room.getBuilding()).append("', ")
                .append(COLUMN_BOARD).append(" = '").append(room.getBoard()).append("', ")
                .append(COLUMN_TEACHER_DESK).append(" = ").append(room.hasTeacherDesk()).append(", ")
                .append(COLUMN_SEATS).append(" = ").append(room.getSeats()).append(", ")
                .append(COLUMN_PROJECTORS).append(" = ").append(room.getProjectors()).append(", ")
                .append(COLUMN_COMPUTERS).append(" = ").append(room.getComputers())
                .append(" WHERE ").append(COLUMN_NAME).append(" = '").append(room.getName().toUpperCase()).append("'");
        this.executeUpdate(sql.toString());
    }

    /**
     * This method is used to get an existing room
     *
     * @param name - String
     * @return Room
     * @throws NullPointerException
     */
    @Override
    public Room getRoom(String name) throws NullPointerException {
        Statement s = null;
        Connection c = ds.getConnection();
        ResultSet rs = null;
        Room room = null;
        try {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ").append(TABLE_NAME)
                    .append(" WHERE ")
                    .append(COLUMN_NAME)
                    .append("='")
                    .append(name)
                    .append("'");
            rs = s.executeQuery(sql.toString());
            if (rs.next()) {
                RoomDirectorBuilder rdb = new RoomDirectorBuilder();
                RoomBuilder rb = rdb.buildRoom(rs.getString(COLUMN_NAME), rs.getString(COLUMN_TYPE), rs.getInt(COLUMN_BUILDING));
                setAllOptionalAttributes(rb, rs);
                room = rdb.getRoom();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(rs);
            this.closeStatement(s);
            ds.closeConnection(c);
        }
        return room;
    }

    /**
     * This method returns all room present in the database
     *
     * @return Vector
     */
    @Override
    public Vector<Room> getAllRooms() {
        Statement s = null;
        Connection c = ds.getConnection();
        ResultSet rs = null;
        Vector<Room> vec = new Vector<>();
        try {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM " + TABLE_NAME);
            rs = s.executeQuery(sql.toString());
            RoomDirectorBuilder rdb = new RoomDirectorBuilder();
            while (rs.next()) {
                Room room = this.buildRoom(rdb, rs);
                vec.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(rs);
            this.closeStatement(s);
            ds.closeConnection(c);
        }
        return vec;
    }

    /**
     * This method returns a vector of rooms as a result of search
     * @param type - String
     * @param building - int
     * @param board - String
     * @param teacherDesk - Boolean
     * @param seats - int
     * @param projectors - int
     * @param computers - int
     * @return Vector
     */
    @Override
    public Vector<Room> getRooms(String type, int building, String board, boolean teacherDesk, int seats, int projectors, int computers) {
        Statement s = null;
        Connection c = ds.getConnection();
        ResultSet rs = null;
        Vector<Room> vec = new Vector<>();
        try {
            s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM " + TABLE_NAME);
            sql.append(" WHERE ");
            sql.append(COLUMN_TEACHER_DESK).append(" = ").append(teacherDesk).append(" ");
            if (!type.isEmpty()) {
                sql.append(" AND ").append(COLUMN_TYPE).append(" = '").append(type).append("' ");
            }
            if (building != -1) {
                sql.append(" AND ").append(COLUMN_BUILDING).append(" = '").append(building).append("' ");
            }
            if (seats != -1) {
                sql.append(" AND ").append(COLUMN_SEATS).append(" >= ").append(seats).append(" ");
            }
            if (projectors != -1) {
                sql.append(" AND ").append(COLUMN_PROJECTORS).append(" >= ").append(projectors).append(" ");
            }
            if (computers != -1) {
                sql.append(" AND ").append(COLUMN_COMPUTERS).append(" >= ").append(computers).append(" ");
            }
            rs = s.executeQuery(sql.toString());
            RoomDirectorBuilder rdb = new RoomDirectorBuilder();
            while (rs.next()) {
                Room room = this.buildRoom(rdb, rs);
                vec.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(rs);
            this.closeStatement(s);
            ds.closeConnection(c);
        }
        return vec;
    }

    /**
     * This method build a room
     *
     * @param rdb - RoomBuilderDirector
     * @param rs  - Result Set
     * @return Room
     * @throws SQLException
     */
    private Room buildRoom(RoomDirectorBuilder rdb, ResultSet rs) throws SQLException {
        RoomBuilder rb = rdb.buildRoom(rs.getString(COLUMN_NAME), rs.getString(COLUMN_TYPE), rs.getInt(COLUMN_BUILDING));
        setAllOptionalAttributes(rb, rs);
        Room room = rdb.getRoom();
        return room;
    }

    /**
     * This method set all otpional attributes
     *
     * @param rb - RoomBuilder
     * @param rs - ResultSet
     * @throws SQLException
     */
    private void setAllOptionalAttributes(RoomBuilder rb, ResultSet rs) throws SQLException {
        rb.setId(rs.getInt(COLUMN_ID))
                .setBoard(rs.getString(COLUMN_BOARD))
                .setTeacherDesk(rs.getBoolean(COLUMN_TEACHER_DESK))
                .setSeats(rs.getInt(COLUMN_SEATS))
                .setProjectors(rs.getInt(COLUMN_PROJECTORS))
                .setComputers(rs.getInt(COLUMN_COMPUTERS));
    }

    /*public static void main(String[] args) {
        RoomDaoDb roomDaoDb = new RoomDaoDb();
        Vector<Room> rooms = roomDaoDb.getRooms("", 2, "", true, -1, -1, -1);
        for (Room room : rooms) {
            System.out.println(room.getName());
        }
    }*/
}