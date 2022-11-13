module com.robbit.askid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens com.robbit.askid to javafx.fxml;
    exports com.robbit.askid;
    opens com.robbit.askid.POJO to javafx.fxml;
    exports com.robbit.askid.POJO;
}