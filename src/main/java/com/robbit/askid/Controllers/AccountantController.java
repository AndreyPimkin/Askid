package com.robbit.askid.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.robbit.askid.POJO.ForAcc;
import com.robbit.askid.server.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AccountantController {
    @FXML
    private Button cancelButton;
    @FXML
    private TableColumn<ForAcc, String> dateTreaty;
    @FXML
    private TableColumn<ForAcc, String> fullName;
    @FXML
    private TableColumn<ForAcc, String> idClient;
    @FXML
    private TableColumn<ForAcc, String> nameProduct;
    @FXML
    private TableColumn<ForAcc, String> numberTreaty;
    @FXML
    private Button okeyButton;
    @FXML
    private TableView<ForAcc> tableAcc;
    private final ObservableList<ForAcc> treatyList = FXCollections.observableArrayList();
    DatabaseHandler dbHandler = new DatabaseHandler();
    private String formattedDate;
    ForAcc selectedNumber;
    ForAcc forAcc;

    @FXML
    void initialize() {
        LocalDate ld = LocalDate.now();
        formattedDate = ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        try {
            initData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        okeyButton.setOnAction(actionEvent -> {
            if(tableAcc.getSelectionModel().getSelectedItem() != null) {
                selectedNumber = tableAcc.getSelectionModel().getSelectedItem();
                forAcc = new ForAcc();
                forAcc.setStatus("Заключен");
                forAcc.setDateTreaty(formattedDate);
                forAcc.setNumberTreaty(selectedNumber.getNumberTreaty());
                try {
                    dbHandler.closeTreaty(forAcc);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                refreshTable();
            } else AuthorizationController.openError("Номер не выбран");


        });
        cancelButton.setOnAction(actionEvent -> {
            if(tableAcc.getSelectionModel().getSelectedItem() != null) {
                selectedNumber = tableAcc.getSelectionModel().getSelectedItem();
                forAcc = new ForAcc();

                forAcc.setStatus("Отклонен");
                forAcc.setDateTreaty(formattedDate);
                forAcc.setNumberTreaty(selectedNumber.getNumberTreaty());
                try {
                    dbHandler.closeTreaty(forAcc);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                refreshTable();
            } else AuthorizationController.openError("Номер не выбран");
        });

        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        numberTreaty.setCellValueFactory(new PropertyValueFactory<>("numberTreaty"));
        dateTreaty.setCellValueFactory(new PropertyValueFactory<>("dateTreaty"));
        nameProduct.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        tableAcc.setItems(treatyList);
    }

    private void initData() throws SQLException {
        dbHandler = new DatabaseHandler();
        ResultSet rs;
        rs = dbHandler.getTreatyForAcc();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            treatyList.add(new ForAcc(rs.getString(1),
                    (rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)),
                    rs.getString(5), rs.getString(6), rs.getString(7)));
        }
    }

    private void refreshTable() {
        tableAcc.getItems().clear();
        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        numberTreaty.setCellValueFactory(new PropertyValueFactory<>("numberTreaty"));
        dateTreaty.setCellValueFactory(new PropertyValueFactory<>("dateTreaty"));
        nameProduct.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        tableAcc.setItems(treatyList);
    }
}
