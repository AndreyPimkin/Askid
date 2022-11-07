module com.robbit.askid {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.robbit.askid to javafx.fxml;
    exports com.robbit.askid;
}