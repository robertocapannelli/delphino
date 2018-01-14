package boundary.roomManagementBoundary.addNewRoomBoundary;

import control.RoomManagementController;
import entity.TemplateRoom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import util.Types;

import java.io.IOException;

public class AddNewRoomActivity {

    //Get the control instance
    private RoomManagementController rmc = RoomManagementController.getInstance();

    @FXML
    private TextField roomName;
    @FXML
    private ComboBox<String> roomType;
    private ObservableList<String> typesList = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Integer> roomBuilding;
    private ObservableList<Integer> buildingsList = FXCollections.observableArrayList();
    @FXML
    private ComboBox<TemplateRoom> roomTemplate;
    private ObservableList<TemplateRoom> roomTemplateList = FXCollections.observableArrayList();
    @FXML
    private TextField roomSeats;
    @FXML
    private TextField roomBoard;
    @FXML
    private TextField roomProjectors;
    @FXML
    private TextField roomComputers;
    @FXML
    private CheckBox roomTeacherDesk;
    @FXML
    private Button returnButton;

    @FXML
    public void initialize() {
        String[] types = Types.getTypes();
        Integer[] buildings = {1, 2, 3};

        //Initialize template combo
        roomTemplateList.add(new TemplateRoom("ciccio", 200, "nera", 2, 20, true));
        roomTemplate.setItems(roomTemplateList);

        //Convert type from template to string, where string is the template name
        roomTemplate.setConverter(new StringConverter<TemplateRoom>() {
            @Override
            public String toString(TemplateRoom object) {
                if (object == null) {
                    return null;
                } else {
                    return object.getNameTemplate();
                }
            }

            @Override
            public TemplateRoom fromString(String string) {
                return null;
            }
        });

        //Initialize type combo
        typesList.addAll(types);
        roomType.setItems(typesList);

        //Initialize building combo
        buildingsList.addAll(buildings);
        roomBuilding.setItems(buildingsList);
    }

    /**
     * This template fill most fields
     * to simplify the insert
     */
    public void fillFromTemplate() {
        TemplateRoom template = roomTemplate.getValue();
        roomSeats.setText(Integer.toString(template.getSeats()));
        roomBoard.setText(template.getBoard());
        roomProjectors.setText(Integer.toString(template.getProjectors()));
        roomComputers.setText(Integer.toString(template.getComputers()));
        roomTeacherDesk.setSelected(template.getDesk());
    }

    /**
     * This method add new room to the database
     */
    public void insertRoom() {
        String name = roomName.getText();
        String type = roomType.getValue();
        int building = roomBuilding.getValue();
        int seats = Integer.parseInt(roomSeats.getText());
        String board = roomBoard.getText();
        int projectors = Integer.parseInt(roomProjectors.getText());
        int computers = Integer.parseInt(roomComputers.getText());
        Boolean desk = roomTeacherDesk.isSelected();

        rmc.addNewRoom(name, type, building, board, desk, seats, projectors, computers);
    }

    public void returnButton() throws IOException {
        Parent root;
        Stage stage = (Stage) returnButton.getScene().getWindow();
        //Check which button has been clicked
        root = FXMLLoader.load(getClass().getResource("/activity/room_management_activity.fxml"));

        //Create and launch the scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}