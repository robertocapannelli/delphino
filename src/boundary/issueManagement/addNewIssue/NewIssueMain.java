package boundary.issueManagement.addNewIssue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewIssueMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("IssueUI.fxml"));

        primaryStage.setTitle("Add new Issue");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
