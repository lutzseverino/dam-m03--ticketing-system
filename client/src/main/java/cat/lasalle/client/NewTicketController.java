package cat.lasalle.client;

import cat.lasalle.client.web.impl.TicketWebResource;
import cat.lasalle.commons.ticket.PriorityDTO;
import cat.lasalle.commons.ticket.StatusDTO;
import cat.lasalle.commons.ticket.TicketDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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
        PriorityDTO[] priorityValues = PriorityDTO.values();
        priorityChoice.getItems().addAll(List.of(priorityValues).stream().map(Enum::name).toList());
        priorityChoice.setValue(PriorityDTO.LOW.name());
        StatusDTO[] statusValues = StatusDTO.values();
        statusChoice.getItems().addAll(List.of(statusValues).stream().map(Enum::name).toList());
        statusChoice.setValue(StatusDTO.OPEN.name());
    }

    public void createTicket(ActionEvent event) {
        TicketWebResource ticketWebResource = new TicketWebResource();
        TicketDTO ticket = new TicketDTO(subjectField.getText());
        ticket.setDescription(descriptionArea.getText());
        ticket.setPriority(PriorityDTO.valueOf(priorityChoice.getValue()));
        ticket.setStatus(StatusDTO.valueOf(statusChoice.getValue()));
        ticketWebResource.create(ticket);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-user-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Main User View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
