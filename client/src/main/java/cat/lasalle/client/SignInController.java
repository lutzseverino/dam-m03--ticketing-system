package cat.lasalle.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;

import java.util.Objects;

public class SignInController {
    @FXML
    public Button showPassword;
    @FXML
    public PasswordField passwordField;
    @FXML
    private ImageView lsStarImage;

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
