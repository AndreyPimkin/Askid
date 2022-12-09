package com.robbit.askid.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.robbit.askid.POJO.ForAdmin;
import com.robbit.askid.POJO.ForClient;
import com.robbit.askid.POJO.ForProduct;
import com.robbit.askid.server.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class AdminController {

    @FXML
    private Button addAcc;
    @FXML
    private Tab accTab;
    @FXML
    private TableColumn<ForAdmin, String> activityColumn;
    @FXML
    private Button addProduct;
    @FXML
    private Button ban;
    @FXML
    private Button changeAcc;
    @FXML
    private Button changeProduct;
    @FXML
    private Tab clientTab;
    @FXML
    private TableColumn<ForAdmin, String> dateFrom;
    @FXML
    private TableColumn<ForProduct, String> dateProductColumn;
    @FXML
    private DatePicker dateProductIn;
    @FXML
    private TableColumn<ForAdmin, String> dateTo;
    @FXML
    private Button deleteAcc;
    @FXML
    private Button deleteProduct;
    @FXML
    private TableColumn<ForAdmin, String> fullName;
    @FXML
    private TableColumn<ForAdmin, String> fullNameColumn;
    @FXML
    private TextField idAcc;
    @FXML
    private TableColumn<ForAdmin, String> idAccColumn;
    @FXML
    private TableColumn<ForAdmin, String> idClient;
    @FXML
    private TableColumn<ForAdmin, String> idClientColumn;
    @FXML
    private TableColumn<ForAdmin, String> idProduct;
    @FXML
    private TableColumn<ForProduct, String> idProductColumn;
    @FXML
    private TextField idProductIn;
    @FXML
    private TableColumn<ForAdmin, String> idTreatyColumn;
    @FXML
    private RadioButton isAcc;
    @FXML
    private TextField login;
    @FXML
    private TextField loginAcc;
    @FXML
    private TableColumn<ForAdmin, String> loginClient;
    @FXML
    private TableColumn<ForAdmin, String> loginColumn;
    @FXML
    private TextField nameAcc;
    @FXML
    private TableColumn<ForProduct, String> nameProductColumn;
    @FXML
    private TextField nameProductIN;
    @FXML
    private TextField numberAcc;
    @FXML
    private TableColumn<ForAdmin, String> numberColumn;
    @FXML
    private Button open;

    @FXML
    private ImageView imagePerson;
    @FXML
    private Button unLogin;
    @FXML
    private TextField passAcc;
    @FXML
    private PasswordField password;
    @FXML
    private TableColumn<ForAdmin, String> passwordColumn;
    @FXML
    private TextField patAcc;
    @FXML
    private TableColumn<ForProduct, String> priceProductColumn;
    @FXML
    private TextField priceProductIn;
    @FXML
    private Tab productTab;
    @FXML
    private TableColumn<ForAdmin, String> status;
    @FXML
    private TextField surnameAcc;
    @FXML
    private TableView<ForAdmin> tableAcc;
    @FXML
    private TableView<ForAdmin> tableClient;
    @FXML
    private TableView<ForProduct> tableProduct;
    @FXML
    private TableView<ForAdmin> tableTreaty;
    @FXML
    private Tab treatyTub;
    DatabaseHandler dbHandler = new DatabaseHandler();
    ForAdmin forAdmin;
    ForClient forClient;
    ForProduct forProduct;
    ResultSet resultAuto;
    ForAdmin selectedClient;

    private final ObservableList<ForAdmin> accList = FXCollections.observableArrayList();
    private final ObservableList<ForAdmin> clientList = FXCollections.observableArrayList();
    private final ObservableList<ForProduct> forProductList = FXCollections.observableArrayList();
    private final ObservableList<ForAdmin> treatyList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        ///////////////////////////////////////////////////////////////////////////////// Авторизация
        accTab.setDisable(true);
        clientTab.setDisable(true);
        productTab.setDisable(true);
        treatyTub.setDisable(true);
        unLogin.setDisable(true);
        unLogin.setOnAction(actionEvent -> {
            accTab.setDisable(true);
            clientTab.setDisable(true);
            productTab.setDisable(true);
            treatyTub.setDisable(true);
            open.setDisable(false);
            unLogin.setDisable(true);
            login.clear();
            password.clear();
            login.setPromptText("Логин");
            password.setPromptText("Пароль");
        });
        imagePerson.setOnMouseClicked(mouseEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginAdmin(loginText, AuthorizationController.getMd5(passwordText));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                AuthorizationController.openError("Пустые данные");
            }
        });
        open.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginAccountant(loginText, AuthorizationController.getMd5(passwordText));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                AuthorizationController.openError("Пустые данные");
            }
        });
        /////////////////////////////////////////////////////////////////////// Бухгалтер
        try {
            initDataAcc();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        changeAcc.setOnAction(actionEvent -> {
            if (!surnameAcc.getText().equals("") && !nameAcc.getText().equals("") && !patAcc.getText().equals("") &&
                    !numberAcc.getText().equals("") && !loginAcc.getText().equals("") && !passAcc.getText().equals("") &&
                    !idAcc.getText().equals("")) {
                forAdmin = new ForAdmin();
                forAdmin.setNameAcc(surnameAcc.getText());
                forAdmin.setSurnameAcc(nameAcc.getText());
                forAdmin.setPatronymicAcc(patAcc.getText());
                forAdmin.setNumberColumn(numberAcc.getText());
                forAdmin.setLoginColumn(loginAcc.getText());
                forAdmin.setPasswordColumn(AuthorizationController.getMd5(passAcc.getText()));
                forAdmin.setIdAccColumn(idAcc.getText());
                try {
                    dbHandler.changeAcc(forAdmin);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                refreshTableAcc();

            } else AuthorizationController.openError("Пустые данные");

        });
        addAcc.setOnAction(actionEvent -> {
            if (!surnameAcc.getText().equals("") && !nameAcc.getText().equals("") && !patAcc.getText().equals("") &&
                    !numberAcc.getText().equals("") && !loginAcc.getText().equals("") && !passAcc.getText().equals("") &&
                    !idAcc.getText().equals("")) {
                forAdmin = new ForAdmin();
                forAdmin.setNameAcc(surnameAcc.getText());
                forAdmin.setSurnameAcc(nameAcc.getText());
                forAdmin.setPatronymicAcc(patAcc.getText());
                forAdmin.setNumberColumn(numberAcc.getText());
                forAdmin.setLoginColumn(loginAcc.getText());
                forAdmin.setPasswordColumn(AuthorizationController.getMd5(passAcc.getText()));
                forAdmin.setIdAccColumn(idAcc.getText());
                try {
                    dbHandler.addAcc(forAdmin);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                refreshTableAcc();
            } else AuthorizationController.openError("Пустые данные");


        });


        deleteAcc.setOnAction(actionEvent -> {
            if (!idAcc.getText().equals("")) {
                forAdmin = new ForAdmin();
                forAdmin.setIdAccColumn(idAcc.getText());
                try {
                    dbHandler.delAcc(forAdmin);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                refreshTableAcc();
            }
            else AuthorizationController.openError("ID не введен");

        });


        idAccColumn.setCellValueFactory(new PropertyValueFactory<>("idAccColumn"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numberColumn"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("loginColumn"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passwordColumn"));
        tableAcc.setItems(accList);
        idAcc.setVisible(false);
        surnameAcc.setVisible(false);
        nameAcc.setVisible(false);
        patAcc.setVisible(false);
        numberAcc.setVisible(false);
        loginAcc.setVisible(false);
        passAcc.setVisible(false);
        deleteAcc.setVisible(false);
        addAcc.setVisible(false);
        changeAcc.setVisible(false);
        isAcc.setOnAction(actionEvent -> {
            if (!(isAcc.isSelected())) {
                tableAcc.setVisible(true);
                isAcc.setText("Добавить");
                idAcc.setVisible(false);
                surnameAcc.setVisible(false);
                nameAcc.setVisible(false);
                patAcc.setVisible(false);
                numberAcc.setVisible(false);
                loginAcc.setVisible(false);
                passAcc.setVisible(false);
                deleteAcc.setVisible(false);
                addAcc.setVisible(false);
                changeAcc.setVisible(false);
            } else {
                tableAcc.setVisible(false);
                isAcc.setText("Таблица");
                idAcc.setVisible(true);
                surnameAcc.setVisible(true);
                nameAcc.setVisible(true);
                patAcc.setVisible(true);
                numberAcc.setVisible(true);
                loginAcc.setVisible(true);
                passAcc.setVisible(true);
                deleteAcc.setVisible(true);
                addAcc.setVisible(true);
                changeAcc.setVisible(true);
            }
        });
        ////////////////////////////////// Пользователь
        try {
            initDataClient();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idClientColumn.setCellValueFactory(new PropertyValueFactory<>("idClientColumn"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullNameColumn"));
        activityColumn.setCellValueFactory(new PropertyValueFactory<>("activityColumn"));
        loginClient.setCellValueFactory(new PropertyValueFactory<>("loginClient"));
        tableClient.setItems(clientList);
        ban.setOnAction(actionEvent -> {
            if (tableClient.getSelectionModel().getSelectedItem() != null) {
                selectedClient = tableClient.getSelectionModel().getSelectedItem();
                forAdmin = new ForAdmin();
                dbHandler = new DatabaseHandler();
                String id = selectedClient.getIdClientColumn();
                ResultSet resultClient;
                forAdmin.setIdAccColumn(id);
                resultClient = dbHandler.getClientAdTwo(forAdmin);
                try {
                    if (resultClient.next()) {
                        if (resultClient.getString("activity").equals("Активен")) {
                            forAdmin.setActivityColumn("Забанен");
                        } else {
                            forAdmin.setActivityColumn("Активен");
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                forAdmin.setIdClientColumn(id);
                try {
                    dbHandler.changeActivity(forAdmin);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                refreshTableClient();
            } else AuthorizationController.openError("Клиент не выбран");

        });
        /////////////////////////////// Продукты
        try {
            initDataProduct();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProductColumn"));
        priceProductColumn.setCellValueFactory(new PropertyValueFactory<>("priceProductColumn"));
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<>("nameProductColumn"));
        dateProductColumn.setCellValueFactory(new PropertyValueFactory<>("dateProductColumn"));
        tableProduct.setItems(forProductList);
        changeProduct.setOnAction(actionEvent -> {
            if (!idProductIn.getText().equals("") && !priceProductIn.getText().equals("") && !nameProductIN.getText().equals("") &&
                    dateProductIn.getValue() != null) {
                forProduct = new ForProduct();
                forProduct.setIdProductColumn(idProductIn.getText());
                forProduct.setPriceProductColumn(priceProductIn.getText());
                forProduct.setNameProductColumn(nameProductIN.getText());
                forProduct.setDateProductColumn(String.valueOf(dateProductIn.getValue()));
                try {
                    dbHandler.changeProduct(forProduct);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                refreshTableProduct();
            } else AuthorizationController.openError("Пустые данные");


        });

        addProduct.setOnAction(actionEvent -> {
            if (!idProductIn.getText().equals("") && !priceProductIn.getText().equals("") && !nameProductIN.getText().equals("") &&
                    dateProductIn.getValue() != null) {
                forProduct = new ForProduct();
                forProduct.setIdProductColumn(idProductIn.getText());
                forProduct.setPriceProductColumn(priceProductIn.getText());
                forProduct.setNameProductColumn(nameProductIN.getText());
                forProduct.setDateProductColumn(String.valueOf(dateProductIn.getValue()));
                try {
                    dbHandler.addProduct(forProduct);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                refreshTableProduct();
            } else AuthorizationController.openError("Пустые данные");

        });

        deleteProduct.setOnAction(actionEvent -> {
            if (!idProductIn.getText().equals("")) {
                forProduct = new ForProduct();
                forProduct.setIdProductColumn(idProductIn.getText());
                try {
                    dbHandler.delProduct(forProduct);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                refreshTableProduct();
            } else AuthorizationController.openError("ID не введен");

        });


        ///////////////////////////////// Договоры
        try {
            initDataTreaty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        idTreatyColumn.setCellValueFactory(new PropertyValueFactory<>("idTreatyColumn"));
        dateFrom.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        dateTo.setCellValueFactory(new PropertyValueFactory<>("dateTo"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        idProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableTreaty.setItems(treatyList);
    }

    private void loginAdmin(String login, String password) throws SQLException {
        forAdmin = new ForAdmin();
        forAdmin.setLogin(login);
        forAdmin.setPasswordAdmin(password);
        resultAuto = dbHandler.autoAdmin(forAdmin);
        if (resultAuto.next()) {
            accTab.setDisable(false);
            clientTab.setDisable(false);
            productTab.setDisable(false);
            treatyTub.setDisable(false);
            open.setDisable(true);
            unLogin.setDisable(false);
        } else AuthorizationController.openError("Администратор не найден");
    }

    private void loginAccountant(String login, String password) throws SQLException {
        forClient = new ForClient();
        forClient.setLogin(login);
        forClient.setPasswordOne(password);
        resultAuto = dbHandler.autoAcc(forClient);
        if (resultAuto.next()) {
            AuthorizationController.openWindow("/com/robbit/askid/accountant.fxml", open, "Бухгалтер");

        } else AuthorizationController.openError("Бухгалтер не найден");
    }

    ResultSet rs;

    private void initDataAcc() throws SQLException {
        dbHandler = new DatabaseHandler();
        rs = dbHandler.getAcc();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            accList.add(new ForAdmin(rs.getString(1), rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7)));
        }
    }

    private void initDataClient() throws SQLException {
        dbHandler = new DatabaseHandler();
        rs = dbHandler.getClientAd();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            clientList.add(new ForAdmin(rs.getString(1), rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4),
                    rs.getString(8), rs.getString(6)));
        }
    }

    private void initDataProduct() throws SQLException {
        dbHandler = new DatabaseHandler();
        rs = dbHandler.getProduct();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            forProductList.add(new ForProduct(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)));
        }
    }

    private void initDataTreaty() throws SQLException {
        dbHandler = new DatabaseHandler();
        rs = dbHandler.getTreaty();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            treatyList.add(new ForAdmin(rs.getString(1), rs.getString(3),
                    rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(2)));
        }
    }

    private void refreshTableAcc() {
        tableAcc.getItems().clear();
        try {
            initDataAcc();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idAccColumn.setCellValueFactory(new PropertyValueFactory<>("idAccColumn"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("numberColumn"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("loginColumn"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passwordColumn"));
        tableAcc.setItems(accList);
    }

    private void refreshTableClient() {
        tableClient.getItems().clear();
        try {
            initDataClient();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idClientColumn.setCellValueFactory(new PropertyValueFactory<>("idClientColumn"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullNameColumn"));
        activityColumn.setCellValueFactory(new PropertyValueFactory<>("activityColumn"));
        loginClient.setCellValueFactory(new PropertyValueFactory<>("loginClient"));
        tableClient.setItems(clientList);
    }

    private void refreshTableProduct() {
        tableProduct.getItems().clear();
        try {
            initDataProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProductColumn"));
        priceProductColumn.setCellValueFactory(new PropertyValueFactory<>("priceProductColumn"));
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<>("nameProductColumn"));
        dateProductColumn.setCellValueFactory(new PropertyValueFactory<>("dateProductColumn"));
        tableProduct.setItems(forProductList);
    }
}


