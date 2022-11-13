package com.robbit.askid.server;

import com.robbit.askid.AuthorizationController;
import com.robbit.askid.POJO.ForAcc;
import com.robbit.askid.POJO.ForClient;
import com.robbit.askid.POJO.Product;
import com.robbit.askid.POJO.Treaty;

import java.sql.*;


public class DatabaseHandler{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionString = "jdbc:sqlserver://DESKTOP-2JU1ID3:1433;databaseName=automated_system;user=sa;password=sa;encrypt=false;";
        dbConnection = DriverManager.getConnection(connectionString);
        return dbConnection;
    }

    public ResultSet autoUser(ForClient forClient){
        ResultSet resSet = null;

        String select = "SELECT * FROM client WHERE login =? AND password = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, forClient.getLogin());
            prSt.setString(2, forClient.getPasswordOne());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

    public ResultSet autoAcc(ForClient forClient){
        ResultSet resSet = null;

        String select = "SELECT * FROM accountant WHERE login =? AND password = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, forClient.getLogin());
            prSt.setString(2, forClient.getPasswordOne());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

    public ResultSet getID(ForClient forClient){
        ResultSet resSet = null;

        String select = "SELECT id_client FROM client WHERE login =? AND password = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, forClient.getLogin());
            prSt.setString(2, forClient.getPasswordOne());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet clientRegCheck(ForClient forClient){
        ResultSet resSet = null;

        String select = "SELECT * FROM client WHERE login =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, forClient.getLogin());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

    public void signUpUser(ForClient forClient) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO  client(login,password, activity)" + "VALUES(?,?, 'Активен')";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, forClient.getLogin());
            prSt.setString(2, forClient.getPasswordOne());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getProduct(){
        ResultSet resSet = null;

        String select = "SELECT * FROM product";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

    public ResultSet getTreatyForClient(){
        ResultSet resSet = null;

        String select = "SELECT treaty.*, product.name  FROM treaty INNER JOIN product ON treaty.id_product = product.id_product " +
                "WHERE id_client = " + AuthorizationController.id_client;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

    public ResultSet getTreatyForAcc(){
        ResultSet resSet = null;

        String select = "SELECT client.id_client, client.surname, client.name, client.patronymic, " +
                "treaty.number_treaty, treaty.date_treaty_from, product.name " +
                "FROM treaty " +
                "INNER JOIN client ON treaty.id_client = client.id_client " +
                "INNER JOIN product ON treaty.id_product = product.id_product " +
                "WHERE treaty.status = 'На рассмотрении'";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

    public void addPersonal(ForClient forClient) throws SQLException, ClassNotFoundException {
        String insert = "UPDATE client SET name=?," +
                "surname=?," +
                "patronymic=?," +
                "birthday=? " +
                "WHERE id_client="+ AuthorizationController.id_client;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, forClient.getName());
            prSt.setString(2, forClient.getSurname());
            prSt.setString(3, forClient.getPatronymic());
            prSt.setString(4, forClient.getDate());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void closeTreaty(ForAcc forAcc) throws SQLException, ClassNotFoundException {
        String insert = "UPDATE treaty SET status= ?, date_treaty_to= ? WHERE number_treaty = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, forAcc.getStatus());
            prSt.setString(2, forAcc.getDateTreaty());
            prSt.setString(3, forAcc.getNumberTreaty());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addDoc(ForClient forClient) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO document (type_doc, number_doc, date_doc)VALUES(?,?,?); " +
                "INSERT INTO document_book (id_doc, id_client)VALUES((SELECT SCOPE_IDENTITY()), ?);";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, forClient.getTypeDoc());
            prSt.setString(2, forClient.getNumberDoc());
            prSt.setString(3, forClient.getDateDoc());
            prSt.setString(4, forClient.getIdClient());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addCon(ForClient forClient) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO contact (type_con, number_con, link_con)VALUES(?,?,?); " +
                "INSERT INTO contact_book (id_con, id_client)VALUES((SELECT SCOPE_IDENTITY()), ?);";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, forClient.getTypeCon());
            prSt.setString(2, forClient.getNumberCon());
            prSt.setString(3, forClient.getLinkCon());
            prSt.setString(4, forClient.getIdClient());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ResultSet getClient(){
        ResultSet resSet = null;

        String select = "SELECT name, surname FROM client WHERE  id_client = " + AuthorizationController.id_client;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void addTreatyClient(Product product, Treaty treaty) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO  treaty(number_treaty, status, date_treaty, id_client, id_product)" +
                "VALUES(?,?,?," + AuthorizationController.id_client +
                ",(SELECT id_product FROM product WHERE  name = ?))" ;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, treaty.getNumberTreaty());
            prSt.setString(2, treaty.getStatusTreaty());
            prSt.setString(3, treaty.getDateTreaty());
            prSt.setString(4, product.getNameProduct());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




}











