package com.robbit.askid;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accAcc;

    @FXML
    private Tab accTab;

    @FXML
    private TableColumn<?, ?> activityColumn;

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
    private Button close;

    @FXML
    private TableColumn<?, ?> dateFrom;

    @FXML
    private TableColumn<?, ?> dateProductColumn;

    @FXML
    private DatePicker dateProductIn;

    @FXML
    private TableColumn<?, ?> dateTo;

    @FXML
    private Button deleteAcc;

    @FXML
    private Button deleteProduct;

    @FXML
    private TableColumn<?, ?> fullName;

    @FXML
    private TableColumn<?, ?> fullNameColumn;

    @FXML
    private TextField idAcc;

    @FXML
    private TableColumn<?, ?> idAccColumn;

    @FXML
    private TableColumn<?, ?> idClient;

    @FXML
    private TableColumn<?, ?> idClientColumn;

    @FXML
    private TableColumn<?, ?> idProduct;

    @FXML
    private TableColumn<?, ?> idProductColumn;

    @FXML
    private TextField idProductIn;

    @FXML
    private TableColumn<?, ?> idTreatyColumn;

    @FXML
    private RadioButton isAcc;

    @FXML
    private TextField login;

    @FXML
    private TextField loginAcc;

    @FXML
    private TableColumn<?, ?> loginClient;

    @FXML
    private TableColumn<?, ?> loginColumn;

    @FXML
    private TextField nameAcc;

    @FXML
    private TableColumn<?, ?> nameProductColumn;

    @FXML
    private TextField nameProductIN;

    @FXML
    private TextField numberAcc;

    @FXML
    private TableColumn<?, ?> numberColumn;

    @FXML
    private Button open;

    @FXML
    private Button openTwo;

    @FXML
    private TextField passAcc;

    @FXML
    private PasswordField password;

    @FXML
    private TableColumn<?, ?> passwordColumn;

    @FXML
    private TextField patAcc;

    @FXML
    private TableColumn<?, ?> priceProductColumn;

    @FXML
    private TextField priceProductIn;

    @FXML
    private Tab productTab;

    @FXML
    private TableColumn<?, ?> status;

    @FXML
    private TextField surnameAcc;

    @FXML
    private TableView<?> tableAcc;

    @FXML
    private TableView<?> tableClient;

    @FXML
    private TableView<?> tableProduct;

    @FXML
    private TableView<?> tableTreaty;

    @FXML
    private Label text;

    @FXML
    private Tab treatyTub;

    @FXML
    void initialize() {

    }

}
