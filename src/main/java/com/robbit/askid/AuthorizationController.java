package com.robbit.askid;
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
import javafx.stage.Stage;

public class AuthorizationController {
    @FXML private Button close;
    @FXML private TextField login;
    @FXML private RadioButton isAcc;
    @FXML private Button open;
    @FXML private Button openReg;
    @FXML private PasswordField password;
    @FXML private Label text;
    public static String id_client = null;
    @FXML void initialize() {

        close.setOnAction(actionEvent -> close.getScene().getWindow().hide());
        openReg.setOnAction(actionEvent -> {
            openWindow("/com/robbit/askid/reg.fxml", open, "Registration");});
        open.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) {
                try {loginUser(loginText, AuthorizationController.getMd5(passwordText));
                } catch (SQLException e) {e.printStackTrace();}
            } else {text.setText("Пустые данные");}});}

    private void loginUser(String login, String passwordOne) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ForClient forClient = new ForClient();
        forClient.setLogin(login);
        forClient.setPasswordOne(passwordOne);
        ResultSet resultAuto = null;
        ResultSet resultAcc = null;
        if (isAcc.isSelected()) {resultAcc = dbHandler.autoAcc(forClient);
        } else {resultAuto = dbHandler.autoUser(forClient);}
        if (isAcc.isSelected() && resultAcc.next())
            openWindow("/com/robbit/askid/acc.fxml", open, "Accountant");
        else if (!isAcc.isSelected()) {
            if(resultAuto.next() && resultAuto.getString("activity").equals("Активен")){
                resultAcc = dbHandler.getID(forClient);
                if (resultAcc.next()){
                    id_client = resultAcc.getString(1);}
                openWindow("/com/robbit/askid/client.fxml", open, "Client");}
            else{text.setText("Вы заблокированы");}}
        else text.setText("Логин или пароль не найдены");}

    private void openWindow(String path, Button button, String title) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {loader.load();
        } catch (IOException e) {e.printStackTrace();}
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.getIcons().add(new Image("file:src/main/resources/picture/ico.png"));
        stage.setTitle(title);
        stage.show();}

    public static String getMd5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;}
            return hashtext;
        } catch (NoSuchAlgorithmException e) {throw new RuntimeException(e);}}}
