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

    public void addUser(Patient patient) throws SQLException, ClassNotFoundException {
        if(connection == null){
            connect();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
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
        preparedStatement.setBoolean(11, false);
        preparedStatement.setBoolean(12, false);
        preparedStatement.execute();
    }

    public void addUser(Physician physician) throws SQLException, ClassNotFoundException
    {
        if(connection == null){
            connect();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1, physician.getEmailAddress());
        preparedStatement.setString(2, physician.getPasswordhash());
        preparedStatement.setString(3, physician.getFirstName());
        preparedStatement.setString(4, physician.getLastName());
        preparedStatement.setString(5, physician.getCity());
        preparedStatement.setString(6, physician.getStreet());
        preparedStatement.setString(7, physician.getHouseNumber());
        preparedStatement.setString(8, physician.getPostalCode());
        preparedStatement.setString(9, physician.getPhoneNUmber());
        preparedStatement.setString(10, physician.getTitle());
        preparedStatement.setBoolean(11, true);
        preparedStatement.setBoolean(12, false);
        preparedStatement.execute();
    }

    public void addUser(Admin admin) throws SQLException, ClassNotFoundException {
        if(connection == null){
            connect();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
        preparedStatement.setString(1, admin.getEmailAddress());
        preparedStatement.setString(2, admin.getPasswordhash());
        preparedStatement.setString(3, admin.getFirstName());
        preparedStatement.setString(4, admin.getLastName());
        preparedStatement.setString(5, admin.getCity());
        preparedStatement.setString(6, admin.getStreet());
        preparedStatement.setString(7, admin.getHouseNumber());
        preparedStatement.setString(8, admin.getPostalCode());
        preparedStatement.setString(9, admin.getPhoneNUmber());
        preparedStatement.setString(10,admin.getTitle());
        preparedStatement.setBoolean(11, false);
        preparedStatement.setBoolean(12, true);
        preparedStatement.execute();
    }

    private void connect() throws SQLException, ClassNotFoundException {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:eHealthcareUsers.db");
            initialise();
    }

    private void initialise() throws SQLException {
        if(!hasData){
            hasData = true;
            Statement state = connection.createStatement();
            ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name='User'");

            if (!res.next()) {
                System.out.println("Building database...");
                buildUserTable();
                buildSpecializationEnumTable();
                buildSpecializationTable();
                buildAppointmentTable();
                buildSeverenessTable();
                buildSymptomTable();
                System.out.println("Database build.");

            }

        }
    }

    private void buildAppointmentTable() throws SQLException{
        System.out.println("Building Appointment table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Appointment (" +
                "AppointmentID int," +
                "PatientID int," +
                "PhysicianID int," +
                "TimeAndDate text," +
                "TransferorderID int," +
                "FOREIGN KEY (PatientID) REFERENCES User (UserID)," +
                "FOREIGN KEY (PhysicianID) REFERENCES User (UserID)" +
                " )");

        System.out.println("complete.");
    }

    private void buildSpecializationTable() throws SQLException {
        System.out.println("Building Specialization table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Specialization (" +
                "UserID int," +
                "Specialization VARCHAR(255)," +
                "PRIMARY KEY (UserID, Specialization)," +
                "FOREIGN KEY (UserID) REFERENCES User (UserID)" +
                ")");

        System.out.println("complete.");
    }

    private void buildSpecializationEnumTable() throws SQLException{
        System.out.println("Building SpecializationEnum table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE SpecializationEnum (" +
                "SpecializationEnumID int IDENTITY(1,1) PRIMARY KEY," +
                "Specialization VARCHAR(255) NOT NULL UNIQUE)");

        System.out.println("complete.");
    }

    private void buildUserTable() throws SQLException{
        System.out.println("Building User table...");
        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE User (" +
                "emailAddress VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "firstName VARCHAR(255) NOT NULL," +
                "lastName VARCHAR (255) NOT NULL," +
                "city VARCHAR(255) NOT NULL," +
                "street VARCHAR(255) NOT NULL," +
                "houseNumber VARCHAR(255) NOT NULL," +
                "postalCode VARCHAR(5)," +
                "phoneNumber VARCHAR(20) NOT NULL," +
                "title VARCHAR (255)," +
                "isPatient BOOLEAN DEFAULT FALSE," +
                "isPhysician BOOLEAN DEFAULT FALSE," +
                "isAdmin BOOLEAN DEFAULT FALSE," +
                "UserID int IDENTITY(1,1) PRIMARY KEY)");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User (" +
                "emailAddress," +
                "password," +
                "firstName," +
                "lastName," +
                "city," +
                "street," +
                "houseNumber," +
                "postalCode," +
                "phoneNumber," +
                "title," +
                "isPatient," +
                "isPhysician," +
                "isAdmin) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");

        preparedStatement.setString(1, "asd");
        preparedStatement.setString(2, User.hashPassword("asd"));
        preparedStatement.setString(3, "Achim");
        preparedStatement.setString(4, "Glaesmann");
        preparedStatement.setString(5, "Frankfurt");
        preparedStatement.setString(6, "Ben-Gurion-Ring");
        preparedStatement.setString(7, "48C");
        preparedStatement.setString(8, "60437");
        preparedStatement.setString(9, "015738340183");
        preparedStatement.setString(10,"hobo");
        preparedStatement.setBoolean(11, false);
        preparedStatement.setBoolean(12,false);
        preparedStatement.setBoolean(13,true);
        preparedStatement.execute();

        System.out.println("complete.");
    }

    private void buildSymptomTable() throws SQLException{
        System.out.println("Building Symptom table...");
        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Symptom (" +
                "name TEXT PRIMARY KEY," +
                "description TEXT NOT NULL);" +
        System.out.println("complete.");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Symptom (" +
                "severeness, " +
                "name," +
                "description," +
                "VALUES (?,?,?);");


    }

    private void buildSeverenessTable() throws  SQLException{
        System.out.println("Building Severeness table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Severeness (" +
                "severenessID int IDENTITY(1,1) PRIMARY KEY," +
                "severeness VARCHAR(255) NOT NULL)");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Severeness (" +
                "severeness) " +
                "VALUES (?);");

        preparedStatement.setString(1, "deadly");
        preparedStatement.execute();

        preparedStatement.setString(1, "very heavy");
        preparedStatement.execute();

        preparedStatement.setString(1, "heavy");
        preparedStatement.execute();

        preparedStatement.setString(1, "medium");
        preparedStatement.execute();

        preparedStatement.setString(1, "light");
        preparedStatement.execute();

        preparedStatement.setString(1, "very light");
        preparedStatement.execute();

        System.out.println("complete.");
    }
}



