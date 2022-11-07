package com.robbit.askid;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private PasswordField confirm_password;

    @FXML
    private TextField id_login;

    @FXML
    private Button open;

    @FXML
    private PasswordField password;

    @FXML
    private Label text;

    @FXML
    void initialize() {
        open.setOnAction(actionEvent -> {
         /*   if (Captcha.check == 1){
                openWindow("/com/example/event/"+ role +".fxml");
            }*/
            String IdText = id_login.getText().trim();
            String passwordText = password.getText().trim();
            String passwordTwoText = confirm_password.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                text.setText("Пустые данные");
                score++;

            }
        });


    }

}
