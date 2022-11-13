package com.robbit.askid;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import com.robbit.askid.POJO.Product;
import com.robbit.askid.POJO.ForClient;
import com.robbit.askid.POJO.Treaty;
import com.robbit.askid.server.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Client {

    @FXML
    private ComboBox<String> boxCon;

    @FXML
    private ComboBox<String> boxDoc;

    @FXML
    private ResourceBundle resources;

    @FXML
    private DatePicker dateClient;

    @FXML
    private DatePicker dateDoc;


    @FXML
    private URL location;

    @FXML
    private Button buyButton;

    @FXML
    private TableColumn<Product, String> dateProduct;

    @FXML
    private TableColumn<Treaty, String> dateTreaty;

    @FXML
    private Button exitButton;

    @FXML
    private TextField linkCon;

    @FXML
    private TableColumn<Product, String> nameProduct;

    @FXML
    private TableColumn<Treaty, String> nameTreaty;

    @FXML
    private TextField nameUser;

    @FXML
    private TextField numberCon;

    @FXML
    private TextField numberDoc;

    @FXML
    private TableColumn<Treaty, String> numberTreaty;

    @FXML
    private TextField patronymicUser;

    @FXML
    private TableColumn<Product, String> price;

    @FXML
    private Button saveButton;

    @FXML
    private TableColumn<Treaty, String> statusTreaty;

    @FXML
    private TableColumn<Treaty, String> dateTreatyTo;

    @FXML
    private TextField surnameUser;

    @FXML
    private TableView<Product> tableProduct;

    @FXML
    private TableView<Treaty> tableTreaty;

    @FXML
    private Label text;

    DatabaseHandler dbHandler = new DatabaseHandler();

    private final ObservableList<Product> productList = FXCollections.observableArrayList();
    private final ObservableList<Treaty> treatyList = FXCollections.observableArrayList();

    private final String[] listDocuments = new String[]{"Паспорт", "СНИЛС", "ИНН", "Полис", "Военный билет"};
    private final String[] listSocialNetwork = new String[]{"Номер телефона", "Электронная почта", "Вконтакте",
            "Viber", "Telegram", "WhatsApp", "ICQ", "ОК"};

    private String selectDocument = "";
    private String selectContact = "";
    private String formattedDate;
    Random random = new Random();



    private int con = 0;
    private int doc = 0;

    @FXML
    void initialize() {
        boxDoc.setValue("Document");
        boxDoc.getItems().addAll(this.listDocuments);
        boxDoc.setOnAction(this::getDocument);

        boxCon.setValue("Contact");
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
        tableProduct.setItems(productList);
        exitButton.setOnAction(actionEvent -> exitButton.getScene().getWindow().hide());

        numberTreaty.setCellValueFactory(new PropertyValueFactory<>("numberTreaty"));
        nameTreaty.setCellValueFactory(new PropertyValueFactory<>("nameTreaty"));
        dateTreaty.setCellValueFactory(new PropertyValueFactory<>("dateTreaty"));
        statusTreaty.setCellValueFactory(new PropertyValueFactory<>("statusTreaty"));
        dateTreatyTo.setCellValueFactory(new PropertyValueFactory<>("dateTreatyTo"));

        tableTreaty.setItems(treatyList);
        exitButton.setOnAction(actionEvent -> exitButton.getScene().getWindow().hide());


        buyButton.setOnAction(actionEvent -> {
            Product product;
            Treaty treaty;
            ResultSet resultClient = null;

            resultClient = dbHandler.getClient();

                    try {
                        if (resultClient.next()){
                            LocalDate ld = LocalDate.now();
                            formattedDate = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            Product selectedProduct = tableProduct.getSelectionModel().getSelectedItem();

                            product = new Product();
                            treaty = new Treaty();

                            product.setNameProduct(selectedProduct.getNameProduct());

                            treaty.setNumberTreaty(generateNumber());
                            treaty.setDateTreaty(formattedDate);
                            treaty.setStatusTreaty("На рассмотрении");

                            try {
                                dbHandler.addTreatyClient(product, treaty);
                            } catch (SQLException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }





                        }
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
                text.setText("Данные обновлены");


            } else {
                text.setText("Присутсвутют пустые данные");
            }


        });

    }

    private String generateNumber(){
        String codeRandom = "";
        int rrr;
        for (int i = 0; i < 10; i++) {
            rrr = random.nextInt(1, 3);
            if (rrr == 1) {
                codeRandom += (char) random.nextInt(65, 90);
            }
            else if (rrr == 2) {
                codeRandom += (char) random.nextInt(48, 57);
            }
            else {
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
            productList.add(new Product(rs.getString("name"), rs.getString("price"), rs.getString("date_product")
            ));
        }
    }

    private void initDataTwo() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        rs = dbHandler.getTreatyForClient();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            treatyList.add(new Treaty(rs.getString("number_treaty"), rs.getString("name"),
                    rs.getString("date_treaty_from"),rs.getString("date_treaty_to") ,rs.getString("status")
            ));
        }
    }

    private void getDocument(ActionEvent actionEvent) {
        selectDocument = boxDoc.getValue();
    }

    private void getContact(ActionEvent actionEvent) {
        selectContact = boxCon.getValue();
    }

}
