package com.robbit.askid.server;

import com.robbit.askid.POJO.Client;

import java.sql.*;


public class DatabaseHandler{
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionString = "jdbc:sqlserver://DESKTOP-2JU1ID3:1433;databaseName=askid;user=sa;password=sa;encrypt=false;";
        dbConnection = DriverManager.getConnection(connectionString);
        return dbConnection;
    }

    public ResultSet autoUser(Client client){
        ResultSet resSet = null;

        String select = "SELECT * FROM client WHERE id_client =? AND password = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select); // Выполняем запрос

            prSt.setString(1, client.getID());
            prSt.setString(2, client.getPasswordOne());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }


}











