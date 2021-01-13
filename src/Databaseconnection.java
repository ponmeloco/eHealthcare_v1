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
        ResultSet resultSet = statement.executeQuery("SELECT firstName, lastName FROM User;");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
    }

    public ResultSet getUser(String email) throws SQLException, ClassNotFoundException {
        if(connection == null){
            connect();
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * FROM User WHERE emailAddress ='" + email + "';");
    }

    public void connect() throws SQLException, ClassNotFoundException {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:eHealthcareUsers.db");
            initialise();
    }

    public void initialise() throws SQLException {
        if(!hasData){
            hasData = true;
            Statement state = connection.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name='User'");

            if (!res.next()) {
                System.out.println("Building User table...");
                Statement state2 = connection.createStatement();
                state2.execute("CREATE TABLE User (emailAddress VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL," +
                        "firstName VARCHAR(255) NOT NULL, lastName VARCHAR (255) NOT NULL," +
                        "city VARCHAR(255) NOT NULL, street VARCHAR(255) NOT NULL, houseNumber VARCHAR(255) NOT NULL, postalCode VARCHAR(5)," +
                        "phoneNumber VARCHAR(20) NOT NULL, title VARCHAR (255));");
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?,?);");
                preparedStatement.setString(1, "achim.glaesmann@outlook.de");
                preparedStatement.setString(2, "");
                preparedStatement.setString(3, "Achim");
                preparedStatement.setString(4, "Glaesmann");
                preparedStatement.setString(5, "Frankfurt");
                preparedStatement.setString(6, "Ben-Gurion-Ring");
                preparedStatement.setString(7, "48C");
                preparedStatement.setString(8, "60437");
                preparedStatement.setString(9, "015738340183");
                preparedStatement.setString(10,"hobo");
                preparedStatement.execute();

            }

        }
    }

    public void addUser(Patient patient) throws SQLException, ClassNotFoundException {
        if(connection == null){
            connect();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1, patient.getEmailAddress());
        preparedStatement.setString(2, patient.getPasswordhash());
        preparedStatement.setString(3, patient.getFirstName());
        preparedStatement.setString(4, patient.getLastName());
        preparedStatement.setString(5, patient.getCity());
        preparedStatement.setString(6, patient.getStreet());
        preparedStatement.setString(7, patient.getHouseNumber());
        preparedStatement.setString(8, patient.getPostalCode());
        preparedStatement.setString(9, patient.getPhoneNUmber());
        preparedStatement.setString(10,patient.getTitle());
        preparedStatement.execute();
    }


}


