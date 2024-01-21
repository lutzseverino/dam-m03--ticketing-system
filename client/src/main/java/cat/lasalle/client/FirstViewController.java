package cat.lasalle.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstViewController {
    @FXML
    public Button connectButton;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-user-view.fxml"));
            Parent root = loader.load();

            // Get the controller for the new scene
//            FirstViewController firstViewController = loader.getController();

            // You can pass data to the new controller if needed
            // differentSceneController.setData(...);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Main User View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
