package com.robbit.askid.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.robbit.askid.POJO.ForClient;
import com.robbit.askid.server.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegistrationController {


    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField login;
    @FXML
    private Button open;
    @FXML
    private Button openAuto;
    @FXML
    private PasswordField password;

    @FXML
    void initialize() {
        openAuto.setOnAction(actionEvent -> AuthorizationController.openWindow("/com/robbit/askid/authorization.fxml", open, "Авторизация"));
        open.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();
            String passwordTwoText = confirmPassword.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("") && !confirmPassword.equals("")) {
                if (passwordText.equals(passwordTwoText)) {
                    try {
                        loginUser(loginText, AuthorizationController.getMd5(passwordText));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    AuthorizationController.openError("Пароли не совпадают");
                }
            } else AuthorizationController.openError("Пустые данные");
        });
    }

    private void loginUser(String login, String passwordOne) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ForClient forClient = new ForClient();
        forClient.setLogin(login);
        ResultSet resultRegCheck = dbHandler.clientRegCheck(forClient);
        try {
            if (resultRegCheck.next()) AuthorizationController.openError("Логин уже используется");
            else {
                forClient = new ForClient();
                forClient.setLogin(login);
                forClient.setPasswordOne(passwordOne);
                try {
                    dbHandler.signUpUser(forClient);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                AuthorizationController.openWindow("/com/robbit/askid/authorization.fxml", open, "Авторизация");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
