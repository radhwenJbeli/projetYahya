/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */

public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/pidev_travel_me";
    public String login="root";
    public String pwd="";


    private static MyConnection instance = null;
    private Connection cnx= null;

    private MyConnection() {}

    private void init() throws SQLException {

        String dbDriver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        cnx = DriverManager.getConnection(url, login, pwd);

        System.out.println("Connected to database");
    }



    public static MyConnection getInstance()  {
        if (instance != null ) {
            return instance;
        } else {
            instance = new MyConnection();
            try {
                instance.init();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }
}
