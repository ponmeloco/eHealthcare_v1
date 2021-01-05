import java.sql.*;
import java.util.Calendar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Date;

/**
 * A Java MySQL PreparedStatement INSERT example.
 * Demonstrates the use of a SQL INSERT statement against a
 * MySQL database, called from a Java program, using a
 * Java PreparedStatement.
 *
 * Created by Alvin Alexander, http://alvinalexander.com
 */
public class sql
{

    public static void sqlInsert()
    {
        try
        {

            // create a mysql database connection
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "sql7.freesqldatabase.com";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "sql7384865", "K25BrrkqtA");

            // create a sql date object so we can use it in our INSERT statement
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            // the mysql insert statement
            String query = " insert into patient (City, Title, Username, Firstname, Lastname)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, "Frankfurt");
            preparedStmt.setString(2, "Dr.");
            preparedStmt.setString(3, "Archy");
            preparedStmt.setString(4, "Achim");
            preparedStmt.setString(5, "Glaesmann");

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}