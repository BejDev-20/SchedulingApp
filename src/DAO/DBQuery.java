package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Iulia Bejsovec
 */
public class DBQuery {
    private static PreparedStatement statement;
    private static ResultSet result;

    public static void setPreparedStatement(Connection conn, String sqlStatement) {
        try {
            statement = (PreparedStatement) conn.createStatement();
            if(sqlStatement.toLowerCase().startsWith("select"))
                result = statement.executeQuery(sqlStatement);
            if(sqlStatement.toLowerCase().startsWith("delete")||sqlStatement.toLowerCase().startsWith("insert")||sqlStatement.toLowerCase().startsWith("update"))
                statement.executeUpdate(sqlStatement);

        }
        catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    public static ResultSet getResult() { return result;}


    public static PreparedStatement getPreparedStatement(){
        return statement;
    }
}
