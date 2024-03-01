package cat.lasalle.client;

import cat.lasalle.client.web.impl.TicketWebResource;
import cat.lasalle.commons.ticket.TicketDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainUserViewController {
    @FXML
    public Button newTicket;
    @FXML
    private TableView<TicketDTO> ticketTable;

    public void initialize() {
        TicketWebResource ticketWebResource = new TicketWebResource();
        List<TicketDTO> tickets = ticketWebResource.readAll(); // Fetch the first 10 tickets
        ObservableList<TicketDTO> observableList = FXCollections.observableArrayList(tickets);
        ticketTable.setItems(observableList); // Bind the tickets to the TableView
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("new-ticket.fxml"));
            Parent root = loader.load();

            // Get the controller for the new scene
            NewTicketController newTicketController = loader.getController();

            // You can pass data to the new controller if needed
//            firstViewController.setData(txtBoxUsername.getText());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.setTitle("Create a New Ticket");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
