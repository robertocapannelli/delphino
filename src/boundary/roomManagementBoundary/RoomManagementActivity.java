package boundary.roomManagementBoundary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RoomManagementActivity {
    @FXML
    private Button addNewRoom;
    @FXML
    private Button viewAllRoom;

    @FXML
    public void initialize() {
    }

    /**
     * This method handle the buttons and dedice which
     * stage open
     * @param e - ActionEvent
     * @throws IOException
     */
    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        Parent root;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //Check which button has been clicked
        if (e.getSource() == addNewRoom) {
            root = FXMLLoader.load(getClass().getResource("addNewRoomBoundary/add_new_room_activity.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("viewAllRoomsBoundary/view_all_rooms_activity.fxml"));
        }
        //Create and launch the scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}