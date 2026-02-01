module com.bd.sgv {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    opens com.bd.sgv.model to javafx.base;

    exports com.bd.sgv;
    opens com.bd.sgv.view to javafx.fxml;
}
