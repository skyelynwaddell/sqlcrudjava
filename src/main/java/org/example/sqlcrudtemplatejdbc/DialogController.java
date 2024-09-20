package org.example.sqlcrudtemplatejdbc; /**
 * Sample Skeleton for 'dialog-view.fxml' Controller Class
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DialogController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="btnDelete"
    private Button btnDelete; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="lblMode"
    private Tab lblMode; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgencyId"
    private TextField txtAgencyId; // Value injected by FXMLLoader

    @FXML // fx:id="txtAgentId"
    private TextField txtAgentId; // Value injected by FXMLLoader

    @FXML // fx:id="txtEmail"
    private TextField txtEmail; // Value injected by FXMLLoader

    @FXML // fx:id="txtFirstName"
    private TextField txtFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="txtIsActive"
    private TextField txtIsActive; // Value injected by FXMLLoader

    @FXML // fx:id="txtLastName"
    private TextField txtLastName; // Value injected by FXMLLoader

    @FXML // fx:id="txtMiddleInitial"
    private TextField txtMiddleInitial; // Value injected by FXMLLoader

    @FXML // fx:id="txtPhone"
    private TextField txtPhone; // Value injected by FXMLLoader

    @FXML // fx:id="txtPosition"
    private TextField txtPosition; // Value injected by FXMLLoader

    private String mode;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert lblMode != null : "fx:id=\"lblMode\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtAgencyId != null : "fx:id=\"txtAgencyId\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtAgentId != null : "fx:id=\"txtAgentId\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtIsActive != null : "fx:id=\"txtIsActive\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtMiddleInitial != null : "fx:id=\"txtMiddleInitial\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtPhone != null : "fx:id=\"txtPhone\" was not injected: check your FXML file 'dialog-view.fxml'.";
        assert txtPosition != null : "fx:id=\"txtPosition\" was not injected: check your FXML file 'dialog-view.fxml'.";

        //Save Button
        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Save();
                CloseStage(mouseEvent);
            }
        });

        //Cancel Button
        btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CloseStage(mouseEvent);
            }
        });
    } // end init

    //Close Stage method to close the window
    private void CloseStage(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    //Save New Entry
    public void Save(){
        int numRows = 0;
        Agent agent = InsertEntry();

        //Change the mode
        try {
            switch (mode) {
                default:
                case "Create":
                    numRows = AgentDB.InsertAgent(agent);
                    break;

                case "Update":
                    numRows = AgentDB.UpdateAgent(agent.getAgentId(),agent);
                    break;
            } // end switch
        } catch (Exception e) {
            throw new RuntimeException(e);
        } // end throw

        //Check if there were any rows returned
        if (numRows == 0){
            new AlertMessage("Error!", "Error! No rows were selected!", Alert.AlertType.ERROR);
        }
        //Finished Inserting New Entry
        else {
            new AlertMessage("Database Operation Completed", mode + "d entry!");
        }
    } // end Save

    //Insert the new entry into the table
    private Agent InsertEntry(){
        Agent agent = new Agent(
                Integer.parseInt(txtAgentId.getText()),
                txtFirstName.getText(),
                txtMiddleInitial.getText(),
                txtLastName.getText(),
                txtPhone.getText(),
                txtEmail.getText(),
                txtPosition.getText(),
                Integer.parseInt(txtAgencyId.getText()),
                Integer.parseInt(txtIsActive.getText())
                );
        return agent;
    } // end InsertEntry

    //Delete Entry method
    public void DeleteEntry(Agent currentEntry, TableView tv){
        int numRows = 0;
        try{
            numRows = AgentDB.DeleteAgent(currentEntry.getAgentId());
            new AlertMessage("Entry Deleted!", "Entry Deleted Success!");

            tv.setItems(null);
            tv.layout();
            tv.setItems(FXCollections.observableList(AgentDB.GetAgents()));
        } catch (SQLException e) {
            new AlertMessage("ERROR!", e.getMessage(), Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }

    //Method to display entry from the agent table in the dialog window to update/delete
    public void DisplayAgent(Agent agent){
        txtAgentId.setText(agent.getAgentId() + "");
        txtFirstName.setText(agent.getAgtFirstName());
        txtMiddleInitial.setText(agent.getAgtMiddleInitial());
        txtLastName.setText(agent.getAgtLastName());
        txtPhone.setText(agent.getAgtBusPhone());
        txtEmail.setText(agent.getAgtEmail());
        txtPosition.setText(agent.getAgtPosition());
        txtAgencyId.setText(agent.getAgencyId() + "");
        txtIsActive.setText(agent.getAgtIsActive() + "");
    }

    //Set Current Mode
    public void SetMode(String mode){
        lblMode.setText(mode);
        this.mode = mode;
    } // end SetMode
} // end class
