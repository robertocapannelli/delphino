package boundary.roomManagementBoundary.viewRoomBoundary;

import controller.RoomManagementController;
import entity.room.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Types;

import java.io.IOException;

public class ViewRoomActivity {

    @FXML private TextField roomName;
    @FXML private ComboBox<String> roomType;
    private ObservableList<String> typesList = FXCollections.observableArrayList();
    @FXML private ComboBox<Integer> roomBuilding;
    private ObservableList<Integer> buildingsList = FXCollections.observableArrayList();
    @FXML private TextField roomSeats;
    @FXML private TextField roomBoard;
    @FXML private TextField roomProjectors;
    @FXML private TextField roomComputers;
    @FXML private CheckBox roomTeacherDesk;
    @FXML private Button deleteButton;
    //This variable is used to get object from tableView
    private Room selectedRoom;

    /**
     * This method get the room object from the room list
     *
     * @param room - Room
     */
    public void getRoomIdFromList(Room room) {
        selectedRoom = room;
        roomName.setText(selectedRoom.getName());
        roomType.setValue(selectedRoom.getType());
        roomBuilding.setValue(selectedRoom.getBuilding());
        roomSeats.setText(Integer.toString(selectedRoom.getSeats()));
        roomBoard.setText(selectedRoom.getBoard());
        roomProjectors.setText(Integer.toString(selectedRoom.getProjectors()));
        roomComputers.setText(Integer.toString(selectedRoom.getComputers()));
        roomTeacherDesk.setSelected(selectedRoom.hasTeacherDesk());
        System.out.println("ViewRoomActivity: getRoomIdFromList " + room.getId());
    }

    @FXML
    public void initialize() {
        //Initialize the types from util
        String[] types = Types.getTypes();
        //TODO we need controller to get all buildings
        //Initialize the buildings from stub
        Integer[] buildings = {1, 2, 3};

        //Initialize type combo
        typesList.addAll(types);
        roomType.setItems(typesList);

        //Initialize building combo
        buildingsList.addAll(buildings);
        roomBuilding.setItems(buildingsList);
        //Disable the user to modify the name field
        roomName.setDisable(true);
    }

    /**
     * This method update a room in database
     */
    public void updateRoom() {
        String name = roomName.getText();
        String type = roomType.getValue();
        int building = roomBuilding.getValue();
        int seats = Integer.parseInt(roomSeats.getText());
        String board = roomBoard.getText();
        int projectors = Integer.parseInt(roomProjectors.getText());
        int computers = Integer.parseInt(roomComputers.getText());
        Boolean desk = roomTeacherDesk.isSelected();
        //Call the controller and update the room
        RoomManagementController rhc = new RoomManagementController();
        rhc.updateRoom(name, type, building, board, desk, seats, projectors, computers);
    }

    /**
     * This method is used to delete a room
     * and get to the previous scene
     */
    public void deleteRoom() throws IOException{
        //Call controller
        RoomManagementController rhc = new RoomManagementController();
        //Call the method to delete the room
        rhc.deleteRoom(selectedRoom);
        //Get back to the table view
        Parent root;
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/activity/view_all_rooms_activity.fxml"));
        //Create and launch the scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
