package com.robbit.askid;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.robbit.askid.POJO.ForClient;
import com.robbit.askid.server.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Reg {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button close;
    @FXML private PasswordField confirmPassword;
    @FXML private TextField login;
    @FXML private Button open;
    @FXML private Button openAuto;
    @FXML private PasswordField password;
    @FXML
    private Label text;
    @FXML
    void initialize() {
        close.setOnAction(actionEvent -> close.getScene().getWindow().hide());
        openAuto.setOnAction(actionEvent -> openWindow("/com/robbit/askid/authorization.fxml", open));
        open.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();
            String passwordTwoText = confirmPassword.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("") && !confirmPassword.equals("")) {
                if (passwordText.equals(passwordTwoText)){
                    try {loginUser(loginText, AuthorizationController.getMd5(passwordText));
                    } catch (SQLException e) {e.printStackTrace();}}
                else{text.setText("Пароли не совпадают");}
            } else text.setText("Пустые данные");});}

    private void loginUser(String login, String passwordOne) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ForClient forClient = new ForClient();
        forClient.setLogin(login);
        ResultSet resultRegCheck = dbHandler.clientRegCheck(forClient);
        try {
            if (resultRegCheck.next()) text.setText("Данный логин уже занят");
            else {
                forClient = new ForClient();
                forClient.setLogin(login);
                forClient.setPasswordOne(passwordOne);
                try {dbHandler.signUpUser(forClient);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();}
                openWindow("/com/robbit/askid/authorization.fxml", open);
            }
        } catch (SQLException e) {e.printStackTrace();}}

    private void openWindow(String path, Button button) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {loader.load();
        } catch (IOException e) {
            e.printStackTrace();}
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.getIcons().add(new Image("file:src/main/resources/picture/ico.png"));
        stage.setTitle("Login");
        stage.show();
    }


}
