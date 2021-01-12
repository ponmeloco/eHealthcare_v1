import java.sql.*;

//source https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html
//source https://www.youtube.com/watch?v=JPsWaI5Z3gs

public class Databaseconnection {
    private static Connection connection;
    private static boolean hasData = false;

    public void displayUsers() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connect();
        }
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT firstName, lastName FROM user;");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
    }

    public ResultSet getUser(String email) throws SQLException, ClassNotFoundException {
        if(connection == null){
            connect();
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT firstName, lastName, password FROM user WHERE emailAddress ='" + email + "';");
    }

    public void connect() throws SQLException, ClassNotFoundException {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:eHealthcareUsers.db");
            initialise();
    }

    private void initialise() throws SQLException {
        if(!hasData){
            hasData = true;
            Statement state = connection.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name='user'");

            if (!res.next()) {
                System.out.println("Building User table...");
                Statement state2 = connection.createStatement();
                state2.execute("CREATE TABLE user (emailAddress VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL," +
                        "firstName VARCHAR(255) NOT NULL, lastName VARCHAR (255) NOT NULL);");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (?,?,?,?);");
                preparedStatement.setString(1, "achim.glaesmann@outlook.de");
                preparedStatement.setString(2, "penis");
                preparedStatement.setString(3, "Achim");
                preparedStatement.setString(4, "Glaesmann");
                preparedStatement.execute();
            }

        }
    }

    public void addUser(String eMail,String password, String firstName, String lastName) throws SQLException, ClassNotFoundException {
        if(connection == null){
            connect();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (?,?,?,?);");
        preparedStatement.setString(1, eMail);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, firstName);
        preparedStatement.setString(4, lastName);
        preparedStatement.execute();
    }


}


