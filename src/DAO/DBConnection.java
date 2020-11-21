package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Make static so there will only be one connection?! SINGLETON
 */
public class DBConnection {
    // JDBC URL parts
    private static final String PROTOCOL = "jdbc";
    private static final String VENDOR_NAME = ":mysql:";
    private static final String IP_ADDRESS = "//wgudb.ucertify.com/WJ07OFg";

    private static final String JDBC_URL = PROTOCOL + VENDOR_NAME + IP_ADDRESS;
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    private static final String USERNAME = "U07OFg";
    private static final String PASSWORD = "53689083159";

    public static Connection startConnection(){
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConn(){
        if (conn == null){
            startConnection();
        }
        return conn;
    }
}
