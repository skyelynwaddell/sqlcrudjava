package org.example.sqlcrudtemplatejdbc;
import javafx.scene.control.Alert;

///Class to create an alert message for the user
public class AlertMessage {

    //Alert message variables
    Alert alert;
    String title;
    String text;
    Alert.AlertType mode;

    //Alert Message Constructor
    public AlertMessage(String title, String text){
        this.title = title;
        this.text = text;
        CreateAlert(Alert.AlertType.INFORMATION);
    }

    //Error Alert Message Constructor
    public AlertMessage(String title, String text, Alert.AlertType mode){
        this.title = title;
        this.text = text;
        this.mode = mode;
        CreateAlert(mode);
    }

    //Create alert method
    private void CreateAlert(Alert.AlertType mode){
        alert = new Alert(mode);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
