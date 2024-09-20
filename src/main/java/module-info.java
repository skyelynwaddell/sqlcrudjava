module org.example.sqlcrudtemplatejdbc {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.sqlcrudtemplatejdbc to javafx.fxml;
    exports org.example.sqlcrudtemplatejdbc;
}