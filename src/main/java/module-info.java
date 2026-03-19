module com.example.guitest {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;

    opens com.session.employee to javafx.fxml;
    opens com.example.guitest to javafx.fxml;
    opens com.table.view to javafx.base;
    exports com.example.guitest;

}