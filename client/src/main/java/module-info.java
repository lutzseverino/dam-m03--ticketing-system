module cat.lasalle.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens cat.lasalle.client to javafx.fxml;
    exports cat.lasalle.client;
}