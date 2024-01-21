package cat.lasalle.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import java.util.Objects;

public class SignInController {
    @FXML
    public Button showPassword;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button signInButton;
    @FXML
    public TextField txtBoxUsername;
    @FXML
    private ImageView lsStarImage;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FirstView.fxml"));
            Parent root = loader.load();

            // Get the controller for the new scene
            FirstViewController firstViewController = loader.getController();

            // You can pass data to the new controller if needed
//            firstViewController.setData(txtBoxUsername.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("First View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isPasswordVisible = false;

    public void initialize() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/images/ls-star.png");
            if (inputStream != null) {
                lsStarImage.setImage(new Image(inputStream));
                System.out.println("Image loaded successfully.");
            } else {
                System.err.println("Image not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading the image: " + e.getMessage());
        }

        ImageView imageView = new ImageView();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/closed-eye.png")));

        if (image.isError()) {
            showPassword.setText("Show password");
            System.err.println("Error loading the image: " + image.getException().getMessage());
        } else {
            // Image loaded successfully, set it as graphic for the button
            imageView.setImage(image);
            imageView.setFitWidth(16);
            imageView.setFitHeight(16);
            showPassword.setGraphic(imageView);
            showPassword.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            showPassword.setPadding(javafx.geometry.Insets.EMPTY);
        }

        showPassword.setOnAction(event -> togglePasswordVisibility());
    }

    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;

        if (isPasswordVisible) {
            passwordField.setPromptText(passwordField.getText());
            passwordField.setText("");
            setOpenEyeIcon();
        } else {
            passwordField.setText(passwordField.getPromptText());
            passwordField.setPromptText("");
            setClosedEyeIcon();
        }
    }

    private void setClosedEyeIcon() {
        ImageView imageView = new ImageView();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/closed-eye.png")));
        imageView.setImage(image);
        imageView.setFitWidth(18);
        imageView.setFitHeight(18);
        showPassword.setGraphic(imageView);
    }

    private void setOpenEyeIcon() {
        ImageView imageView = new ImageView();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/eye.png")));
        imageView.setImage(image);
        imageView.setFitWidth(18);
        imageView.setFitHeight(18);
        showPassword.setGraphic(imageView);
    }
}
