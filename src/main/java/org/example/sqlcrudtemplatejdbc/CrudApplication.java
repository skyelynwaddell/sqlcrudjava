package org.example.sqlcrudtemplatejdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CrudApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Skye Waddell | 9-19-2024
        // SQL Crud Template using Java JDBC
        // DB: travelexperts

        final String app_name  = "SQL CRUD TEMPLATE JDBC";
        final String fxml_file = "sqlcrud-view.fxml";

        //Create Main Page
        FXMLLoader fxmlLoader = new FXMLLoader(CrudApplication.class.getResource(fxml_file));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(app_name);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}