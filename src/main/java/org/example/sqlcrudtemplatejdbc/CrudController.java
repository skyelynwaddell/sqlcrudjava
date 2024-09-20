package org.example.sqlcrudtemplatejdbc;

/**
 * Sample Skeleton for 'sqlcrud-view.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CrudController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // fx:id="tvAgents"
    private TableView<Agent> tvAgents; // Value injected by FXMLLoader

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreate"
    private Button btnCreate; // Value injected by FXMLLoader

    @FXML // fx:id="btnDelete"
    private Button btnDelete; // Value injected by FXMLLoader

    @FXML // fx:id="btnUpdate"
    private Button btnUpdate; // Value injected by FXMLLoader

    @FXML // fx:id="colAgencyId"
    private TableColumn<Agent, Integer> colAgencyId; // Value injected by FXMLLoader

    @FXML // fx:id="colEmail"
    private TableColumn<Agent, String> colEmail; // Value injected by FXMLLoader

    @FXML // fx:id="colFirstName"
    private TableColumn<Agent, String> colFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="colId"
    private TableColumn<Agent, Integer> colId; // Value injected by FXMLLoader

    @FXML // fx:id="colIsActive"
    private TableColumn<Agent, Integer> colIsActive; // Value injected by FXMLLoader

    @FXML // fx:id="colLastName"
    private TableColumn<Agent, String> colLastName; // Value injected by FXMLLoader

    @FXML // fx:id="colMiddleInitial"
    private TableColumn<Agent, String> colMiddleInitial; // Value injected by FXMLLoader

    @FXML // fx:id="colPhone"
    private TableColumn<Agent, String> colPhone; // Value injected by FXMLLoader

    @FXML // fx:id="colPosition"
    private TableColumn<Agent, String> colPosition; // Value injected by FXMLLoader

    //Custom properties
    private ObservableList<Agent> data = FXCollections.observableArrayList();
    private String mode;
    private Agent currentAgent = null;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreate != null : "fx:id=\"btnCreate\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colAgencyId != null : "fx:id=\"colAgencyId\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colEmail != null : "fx:id=\"colEmail\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colFirstName != null : "fx:id=\"colFirstName\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colId != null : "fx:id=\"colId\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colIsActive != null : "fx:id=\"colIsActive\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colLastName != null : "fx:id=\"colLastName\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colMiddleInitial != null : "fx:id=\"colMiddleInitial\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colPhone != null : "fx:id=\"colPhone\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert colPosition != null : "fx:id=\"colPosition\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";
        assert tvAgents != null : "fx:id=\"tvAgents\" was not injected: check your FXML file 'sqlcrud-view.fxml'.";

        //set up table columns
        colId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agentId"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtFirstName"));
        colMiddleInitial.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtMiddleInitial"));
        colLastName.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtLastName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtBusPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtEmail"));
        colPosition.setCellValueFactory(new PropertyValueFactory<Agent, String>("agtPosition"));
        colAgencyId.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agencyId"));
        colIsActive.setCellValueFactory(new PropertyValueFactory<Agent, Integer>("agtIsActive"));
        DisplayAgents();

        //CREATE BUTTON
        btnCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NewDialogWindow(null,"Create");
            }
        });

        //UPDATE BUTTON
        btnUpdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                NewDialogWindow(currentAgent,"Update");
            }
        });

        //DELETE BUTTON
        btnDelete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Delete();
            }
        });

        //USER SELECTION FROM TABLE VIEW
        tvAgents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agent>() {
            @Override
            public void changed(ObservableValue<? extends Agent> observableValue, Agent agent, Agent t1) {
               GetCurrentAgent(t1);
            }
        });
    }

    //Get current agent selected in the table view
    private void GetCurrentAgent(Agent t1){
        int index = tvAgents.getSelectionModel().getSelectedIndex();

        //check if this is selection, opposed to de-select
        if (tvAgents.getSelectionModel().isSelected(index)){
            currentAgent = t1;
        }
    }

    //Delete Button
    private void Delete(){
        //Call the delete entry method from the dialog controller
        DialogController dc = new DialogController();
        dc.DeleteEntry(currentAgent,tvAgents);
    }

    //Display Agents Method and populate the table view
    private void DisplayAgents(){
        data.clear();
        try {
            data = AgentDB.GetAgents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tvAgents.setItems(data);
    }

    //Create new dialog window with mode as parameter with try catch as method
    private void NewDialogWindow(Agent agent, String mode){
        this.mode = mode;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    OpenDialog(agent,mode);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //Open Dialog Popup Window
    //Pass agent as null for Create dialog, since there is no agent selected
    private void OpenDialog(Agent agent, String mode) throws IOException {
        final String fxml_file = "dialog-view.fxml";

        //Create dialog scene
        FXMLLoader fxmlLoader = new FXMLLoader(CrudApplication.class.getResource(fxml_file));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //set stage, show window and display all agents
        Stage stage = new Stage();
        DialogController dialogController = fxmlLoader.getController();
        dialogController.SetMode(mode);

        //Check if we have an agent selected for the update method
        //Update Mode With Current Agent Selected
        if (mode.equals("Update") && currentAgent != null){
            System.out.println("Update Mode Selected");
            dialogController.DisplayAgent(agent);
        //Update Mode with NULL Current Agent Selected
        } else if (mode.equals("Update") && currentAgent == null){
            new AlertMessage("No Entry Selected","No entry selected. Please select an entry first to update it.");
            return;
        //Create Mode
        } else if (mode.equals("Create")){
            System.out.println("Create Mode Selected");
        }

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        DisplayAgents();
    }
}
