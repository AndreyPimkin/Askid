module com.robbit.askid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens com.robbit.askid to javafx.fxml;
    exports com.robbit.askid;
}