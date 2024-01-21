package cat.lasalle.client;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewTicketController {
    @FXML
    public TextField subjectField;
    @FXML
    public TextArea descriptionArea;
    @FXML
    public ChoiceBox<String> priorityChoice;
    @FXML
    public TextField requesterField;
    @FXML
    public ChoiceBox<String> statusChoice;

    public void initialize() {
        priorityChoice.getItems().addAll("LOW", "MEDIUM", "HIGH", "CRITICAL");
        priorityChoice.setValue("LOW");
        statusChoice.getItems().addAll("OPEN", "IN_PROGRESS", "RESOLVED", "CLOSED");
        statusChoice.setValue("OPEN");
    }
}
