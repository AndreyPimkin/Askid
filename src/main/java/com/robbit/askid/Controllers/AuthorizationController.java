package com.robbit.askid.Controllers;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.robbit.askid.POJO.ForClient;
import com.robbit.askid.server.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AuthorizationController {

    @FXML
    private TextField login;
    @FXML
    private Button open;
    @FXML
    private Button openReg;
    @FXML
    private PasswordField password;

    public static String id_client = null;

    @FXML
    void initialize() {

        openReg.setOnAction(actionEvent -> {
            openWindow("/com/robbit/askid/registration.fxml", open, "Регистрация");
        });
        open.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, AuthorizationController.getMd5(passwordText));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                openError("Пустые данные");
            }
        });
    }

    private void loginUser(String login, String passwordOne) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ForClient forClient = new ForClient();
        forClient.setLogin(login);
        forClient.setPasswordOne(passwordOne);
        ResultSet resultAuto;
        resultAuto = dbHandler.autoUser(forClient);
        if (resultAuto.next()) {
            forClient.setLogin(login);
            forClient.setPasswordOne(passwordOne);
            resultAuto = dbHandler.autoUser(forClient);
            if (resultAuto.next() && resultAuto.getString("activity").equals("Активен")) {
                id_client = resultAuto.getString(1);
                openWindow("/com/robbit/askid/client.fxml", open, "Клиент");
            }
            else {
                openError("Вы заблокированы");
            }
        }
        else openError("Пользователь не найден");
    }

    public static void openWindow(String path, Button button, String title) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AuthorizationController.class.getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.getIcons().add(new Image("file:src/main/resources/picture/ico.png"));
        stage.setTitle(title);
        stage.show();
    }

    public static String getMd5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static void openError(String info) {
        ErrorController.massage = info;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AuthorizationController.class.getResource("/com/robbit/askid/error.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.getIcons().add(new Image("file:src/main/resources/picture/ico.png"));
        stage.setTitle("Ошибка");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }


}
