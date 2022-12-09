module com.robbit.askid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires com.microsoft.sqlserver.jdbc;

    opens com.robbit.askid to javafx.fxml;
    exports com.robbit.askid;
    opens com.robbit.askid.POJO to javafx.fxml;
    exports com.robbit.askid.POJO;
    exports com.robbit.askid.Controllers;
    opens com.robbit.askid.Controllers to javafx.fxml;

    exports com.robbit.askid.test;
    opens com.robbit.askid.test to javafx.fxml;

}