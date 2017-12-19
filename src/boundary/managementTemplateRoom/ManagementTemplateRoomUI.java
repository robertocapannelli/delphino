package boundary.managementTemplateRoom;

import boundary.managementTemplateRoom.createTemplateRoom.MainCreateTemplateRoom;
import boundary.managementTemplateRoom.modifyDeleteTemplateRoom.MainModifyDeleteTemplateRoom;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Main;


public class ManagementTemplateRoomUI {

    @FXML
    private Button modifyTemplateRoomBtn;
    @FXML
    private Button createTemplateRoomBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button homePageBtn;

    public void createTemplateRoomScene() throws Exception {
        MainCreateTemplateRoom C = new MainCreateTemplateRoom();
        try {
            C.start(new Stage());
            Stage stage = (Stage) createTemplateRoomBtn.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainPrev() throws Exception {
        Main M = new Main();
        try {
            M.start(new Stage());
            Stage stage = (Stage) homePageBtn.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyTemplateRoomScene() throws Exception {
        MainModifyDeleteTemplateRoom M = new MainModifyDeleteTemplateRoom();
        try {
            M.start(new Stage());
            Stage stage = (Stage) modifyTemplateRoomBtn.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeWindow(){
        try {
            Stage stage = (Stage) exitBtn.getScene().getWindow();
            stage.close();

        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void initialize(){

    }




}
