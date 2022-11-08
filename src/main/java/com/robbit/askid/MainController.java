package com.robbit.askid;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.robbit.askid.POJO.Client;
import com.robbit.askid.server.DatabaseHandler;
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
            if (!IdText.equals("") && !passwordText.equals("") && !passwordTwoText.equals("")) {
                if(passwordText.equals(passwordTwoText)){
                    try {
                        loginUser(IdText, passwordText, passwordTwoText);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                else{
                    text.setText("Пароли не совпадают");
                }

            }
            else {
                text.setText("Пустые данные");
            }
        });
    }

    private void loginUser(String id, String passwordOne, String passwordTwo) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Client client = new Client();
        client.setID(id);
        client.setPasswordOne(passwordOne);
        client.setPasswordTwo(passwordTwo);

        ResultSet resultAuto = dbHandler.autoUser(client);

        if(resultAuto.next()){
            System.out.println("Найден");
        }
        else text.setText("Такой пользователь не существует");
    }

}
