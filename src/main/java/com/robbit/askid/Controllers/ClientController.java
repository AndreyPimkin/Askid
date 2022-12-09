package com.robbit.askid.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.robbit.askid.POJO.ForProduct;
import com.robbit.askid.POJO.ForClient;
import com.robbit.askid.POJO.ForTreaty;
import com.robbit.askid.server.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ClientController {

    @FXML
    private ComboBox<String> boxCon;
    @FXML
    private ComboBox<String> boxDoc;
    @FXML
    private DatePicker dateClient;
    @FXML
    private DatePicker dateDoc;
    @FXML
    private Button buyButton;
    @FXML
    private TableColumn<ForProduct, String> dateProduct;
    @FXML
    private TableColumn<ForTreaty, String> dateTreaty;
    @FXML
    private TextField linkCon;
    @FXML
    private TableColumn<ForProduct, String> nameProduct;
    @FXML
    private TableColumn<ForTreaty, String> nameTreaty;
    @FXML
    private TextField nameUser;
    @FXML
    private TextField numberCon;
    @FXML
    private TextField numberDoc;
    @FXML
    private TableColumn<ForTreaty, String> numberTreaty;
    @FXML
    private TextField patronymicUser;
    @FXML
    private TableColumn<ForProduct, String> price;
    @FXML
    private Button saveButton;
    @FXML
    private TableColumn<ForTreaty, String> statusTreaty;
    @FXML
    private TableColumn<ForTreaty, String> dateTreatyTo;
    @FXML
    private TextField surnameUser;
    @FXML
    private TableView<ForProduct> tableProduct;
    @FXML
    private TableView<ForTreaty> tableTreaty;
    DatabaseHandler dbHandler = new DatabaseHandler();
    private final ObservableList<ForProduct> forProductList = FXCollections.observableArrayList();
    private final ObservableList<ForTreaty> forTreatyList = FXCollections.observableArrayList();
    private final String[] listDocuments = new String[]{"Паспорт", "СНИЛС", "ИНН", "Полис", "Военный билет"};
    private final String[] listSocialNetwork = new String[]{"Номер телефона", "Электронная почта", "Вконтакте",
            "Viber", "Telegram", "WhatsApp", "ICQ", "ОК"};

    private String selectDocument;
    private String selectContact;
    private String formattedDate;
    Random random = new Random();
    private int con = 0;
    private int doc = 0;
    ForProduct forProduct;
    ForTreaty forTreaty;

    ForProduct selectedForProduct;
    @FXML
    void initialize() {


        boxDoc.getItems().addAll(this.listDocuments);
        boxDoc.setOnAction(this::getDocument);
        boxCon.getItems().addAll(this.listSocialNetwork);
        boxCon.setOnAction(this::getContact);
        try {
            initData();
            initDataTwo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        nameProduct.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        dateProduct.setCellValueFactory(new PropertyValueFactory<>("dateProduct"));
        tableProduct.setItems(forProductList);
        numberTreaty.setCellValueFactory(new PropertyValueFactory<>("numberTreaty"));
        nameTreaty.setCellValueFactory(new PropertyValueFactory<>("nameTreaty"));
        dateTreaty.setCellValueFactory(new PropertyValueFactory<>("dateTreaty"));
        statusTreaty.setCellValueFactory(new PropertyValueFactory<>("statusTreaty"));
        dateTreatyTo.setCellValueFactory(new PropertyValueFactory<>("dateTreatyTo"));
        tableTreaty.setItems(forTreatyList);
        buyButton.setOnAction(actionEvent -> {
            ResultSet resultClient;
            LocalDate ld = LocalDate.now();
            formattedDate = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            resultClient = dbHandler.getClient();

            try {
                if (resultClient.next()) {
                    if(tableProduct.getSelectionModel().getSelectedItem() != null){
                        forProduct = new ForProduct();
                        forTreaty = new ForTreaty();
                        selectedForProduct = tableProduct.getSelectionModel().getSelectedItem();

                        forProduct.setNameProduct(selectedForProduct.getNameProduct());
                        forTreaty.setNumberTreaty(generateNumber());
                        forTreaty.setDateTreaty(formattedDate);
                        forTreaty.setStatusTreaty("На рассмотрении");
                        try {
                            dbHandler.addTreatyClient(forProduct, forTreaty);
                            refreshTable();
                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else AuthorizationController.openError("Пустые данные");

                } else AuthorizationController.openError("Заполните профиль");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        saveButton.setOnAction(actionEvent -> {
            String nameUserText = nameUser.getText().trim();
            String surnameUserText = surnameUser.getText().trim();
            String patronymicUserText = patronymicUser.getText().trim();
            String numberDocText = numberDoc.getText().trim();
            String linkConText = linkCon.getText().trim();
            String numberConText = numberCon.getText().trim();
            ForClient forClient;
            if (!nameUserText.equals("") && !surnameUserText.equals("") && !patronymicUserText.equals("") && dateClient.getValue() != null ||
                    ((!linkConText.equals("") || !numberConText.equals("")) && !selectContact.equals("")) ||
                    (!numberDocText.equals("") && dateDoc.getValue() != null) && !selectDocument.equals("")) {
                if (!nameUserText.equals("") && !surnameUserText.equals("") && !patronymicUserText.equals("") && dateClient.getValue() != null) {
                    forClient = new ForClient();
                    forClient.setName(nameUserText);
                    forClient.setSurname(surnameUserText);
                    forClient.setPatronymic(patronymicUserText);
                    forClient.setDate(String.valueOf(dateClient.getValue()));
                    try {
                        dbHandler.addPersonal(forClient);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if ((!linkConText.equals("") || !numberConText.equals("")) && !selectContact.equals("") && con == 0) {
                    forClient = new ForClient();
                    forClient.setTypeCon(selectContact);
                    forClient.setLinkCon(linkConText);
                    forClient.setNumberCon(numberConText);
                    forClient.setIdClient(AuthorizationController.id_client);
                    try {
                        dbHandler.addCon(forClient);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    con = 1;
                }
                if (!numberDocText.equals("") && dateDoc.getValue() != null && !selectDocument.equals("") && doc == 0) {
                    forClient = new ForClient();
                    forClient.setTypeDoc(selectDocument);
                    forClient.setNumberDoc(numberDocText);
                    forClient.setDateDoc(String.valueOf(dateDoc.getValue()));
                    forClient.setIdClient(AuthorizationController.id_client);
                    try {
                        dbHandler.addDoc(forClient);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    doc = 1;
                }
                saveButton.setText("Успешно");
            } else AuthorizationController.openError("Пустые данные");
        });
    }

    private String generateNumber() {
        String codeRandom = "";
        int rrr;
        for (int i = 0; i < 10; i++) {
            rrr = random.nextInt(1, 3);
            if (rrr == 1) {
                codeRandom += (char) random.nextInt(65, 90);
            } else if (rrr == 2) {
                codeRandom += (char) random.nextInt(48, 57);
            } else {
                codeRandom += (char) random.nextInt(97, 122);
            }
        }
        return codeRandom;
    }

    private void initData() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        rs = dbHandler.getProduct();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            forProductList.add(new ForProduct(rs.getString("name"), rs.getString("price"), rs.getString("date_product")));
        }
    }

    private void initDataTwo() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        rs = dbHandler.getTreatyForClient();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            forTreatyList.add(new ForTreaty(rs.getString("number_treaty"), rs.getString("name"),
                    rs.getString("date_treaty_from"), rs.getString("date_treaty_to"), rs.getString("status")));
        }
    }

    private void refreshTable() {
        tableTreaty.getItems().clear();
        try {
            initDataTwo();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        numberTreaty.setCellValueFactory(new PropertyValueFactory<>("numberTreaty"));
        nameTreaty.setCellValueFactory(new PropertyValueFactory<>("nameTreaty"));
        dateTreaty.setCellValueFactory(new PropertyValueFactory<>("dateTreaty"));
        statusTreaty.setCellValueFactory(new PropertyValueFactory<>("statusTreaty"));
        dateTreatyTo.setCellValueFactory(new PropertyValueFactory<>("dateTreatyTo"));
        tableTreaty.setItems(forTreatyList);
    }


    private void getDocument(ActionEvent actionEvent) {
        selectDocument = boxDoc.getValue();
    }

    private void getContact(ActionEvent actionEvent) {
        selectContact = boxCon.getValue();
    }

    public void mouse(MouseEvent mouseEvent) {
        saveButton.setText("Сохранить");
    }
}
