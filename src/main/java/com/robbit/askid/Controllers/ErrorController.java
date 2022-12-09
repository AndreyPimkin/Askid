package com.robbit.askid.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ErrorController {
    public static String massage = "";

    @FXML
    private Label info;

    @FXML
    private Button okButton;


    @FXML
    void initialize() {
        info.setText(massage);
        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });
    }
}
