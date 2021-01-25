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
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User(emailAddress, password, firstName, lastName, city, street, houseNumber, postalCode, phoneNumber, title) VALUES (?,?,?,?,?,?,?,?,?,?);");
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
    public void addUser(Physician physician) throws SQLException, ClassNotFoundException
    {
        if(connection == null){
            connect();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User(emailAddress, password, firstName, lastName, city, street, houseNumber, postalCode, phoneNumber, title) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
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
        preparedStatement.execute();
    }
    public void addUser(Admin admin) throws SQLException, ClassNotFoundException {
        if(connection == null){
            connect();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User(emailAddress, password, firstName, lastName, city, street, houseNumber, postalCode, phoneNumber, title) VALUES (?,?,?,?,?,?,?,?,?,?);");
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

        preparedStatement.execute();
    }

    public Patient getPatient(String email) throws SQLException, ClassNotFoundException{
        if(connection == null){
            connect();
        }
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT User.* FROM User WHERE emailAddress ='" + email + "';");

        System.out.println("Printing Result and setting variables.");
        System.out.println(res.getString(2)); // email

        System.out.println(res.getString(3));//pwhash
        String pwhash = res.getString(3);

        System.out.println(res.getString(4)); //firstname
        String firstName = res.getString(4);

        System.out.println(res.getString(5)); //lastname
        String lastName = res.getString(5);

        System.out.println(res.getString(6)); //city
        String city = res.getString(6);

        System.out.println(res.getString(7)); //Street
        String street = res.getString(7);

        System.out.println(res.getString(8)); //houseNumber
        String houseNumber = res.getString(8);

        System.out.println(res.getString(9)); //postalCode
        String postalCode = res.getString(9);

        System.out.println(res.getString(10));//phoneNumber
        String phoneNumber = res.getString(10);

        System.out.println(res.getString(11));//title
        String title = res.getString(11);

        System.out.println("Done");

        System.out.println("Creating user");
        Patient result = new Patient(email,firstName,lastName,city,street,houseNumber,postalCode,
                phoneNumber, title, pwhash);
        System.out.println("Patientobject created... \n returning Object...");
        return result;
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
                buildPhysicianTable();
                buildPatientTable();
                buildAdminTable();
                buildSpecializationTable();
                buildSpecializationPhysicianTable();
                buildSymptomTable();
                buildSeverenessTable();
                buildSymptomPatientTable();
                buildDrugTable();
                buildMedicationtable();
                buildAppointmentTable();
                buildRatingTable();
                buildThumbTable();
                buildTagTable();
                buildRatingTagTable();
                buildTransferOrderTable();
                System.out.println("Database build.");

            }

        }
    }

    private void buildUserTable() throws SQLException{
        System.out.println("Building User table...");
        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE User (" +
                "ID INTEGER PRIMARY KEY," +
                "emailAddress VARCHAR(255) NOT NULL UNIQUE," +
                "password VARCHAR(255) NOT NULL," +
                "firstName VARCHAR(255) NOT NULL," +
                "lastName VARCHAR (255) NOT NULL," +
                "city VARCHAR(255) NOT NULL," +
                "street VARCHAR(255) NOT NULL," +
                "houseNumber VARCHAR(255) NOT NULL," +
                "postalCode VARCHAR(5) NOT NULL," +
                "phoneNumber VARCHAR(20) NOT NULL," +
                "title VARCHAR (255) NOT NULL" +
                ")");

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
                "title)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?);");

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
        preparedStatement.execute();

        preparedStatement.setString(1, "asf");
        preparedStatement.setString(2, User.hashPassword("asd"));
        preparedStatement.setString(3, "Achim");
        preparedStatement.setString(4, "Glaesmann");
        preparedStatement.setString(5, "Frankfurt");
        preparedStatement.setString(6, "Ben-Gurion-Ring");
        preparedStatement.setString(7, "48C");
        preparedStatement.setString(8, "60437");
        preparedStatement.setString(9, "015738340183");
        preparedStatement.setString(10,"hobo");
        preparedStatement.execute();

        System.out.println("complete.");
    }
    private void buildPhysicianTable() throws SQLException {
        System.out.println("Building Physician table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Physician (" +
                "ID int NOT NULL," +
                "PRIMARY KEY(ID), " +
                "FOREIGN KEY(ID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");

        System.out.println("complete.");
    }
    private void buildPatientTable() throws SQLException {
        System.out.println("Building Patient table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Patient (" +
                "ID int NOT NULL," +
                "PRIMARY KEY(ID), " +
                "FOREIGN KEY(ID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Patient (" +
               "ID) VALUES (?)");

        preparedStatement.setString(1, "1");
        preparedStatement.execute();

        System.out.println("complete.");
    }
    private void buildAdminTable() throws SQLException {
        System.out.println("Building Admin table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Admin (" +
                "ID int NOT NULL," +
                "PRIMARY KEY(ID), " +
                "FOREIGN KEY(ID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");

        System.out.println("complete.");
    }
    private void buildSpecializationTable() throws SQLException{
        System.out.println("Building Specialization table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Specialization (" +
                "ID INTEGER PRIMARY KEY," +
                "Specialization VARCHAR(255) NOT NULL UNIQUE"+
                ")");

        System.out.println("complete.");
    }
    private void buildSpecializationPhysicianTable() throws SQLException {
        System.out.println("Building SpecializationPhysician table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE SpecializationPhysician (" +
                "ID INTEGER PRIMARY KEY," +
                "SpecializationID INT," +
                "PhysicianID INT," +
                "FOREIGN KEY (SpecializationID) REFERENCES Specialization(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (PhysicianID) REFERENCES Physician(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");

        System.out.println("complete.");
    }
    private void buildSymptomTable() throws SQLException{
        System.out.println("Building Symptom table...");
        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Symptom (" +
                "ID INTEGER PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "description VARCHAR(255) NOT NULL" +
                ");");
        System.out.println("complete.");
    }
    private void buildSeverenessTable() throws  SQLException{
        System.out.println("Building Severeness table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Severeness (" +
                "ID INTEGER PRIMARY KEY," +
                "severeness VARCHAR(255) NOT NULL" +
                ")");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Severeness (" +
                "severeness) " +
                "VALUES (?);");

        preparedStatement.setString(1, "deadly");
        preparedStatement.execute();

        preparedStatement.setString(1, "heavy");
        preparedStatement.execute();

        preparedStatement.setString(1, "medium");
        preparedStatement.execute();

        preparedStatement.setString(1, "light");
        preparedStatement.execute();

        System.out.println("complete.");
    }
    private void buildSymptomPatientTable() throws SQLException{
        System.out.println("Building SymptomPatient table...");
        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE SymptomPatient (" +
                "ID INTEGER PRIMARY KEY," +
                "PatientID INT," +
                "SymptomID INT," +
                "SeverenessID INT," +
                "FOREIGN KEY (PatientID) REFERENCES Patient(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (SymptomID) REFERENCES Symptom(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (SeverenessID) REFERENCES Severeness(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");
        System.out.println("complete.");
    }
    private void buildDrugTable() throws SQLException{
        System.out.println("Building Drug table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Drug (" +
                "ID INTEGER PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "activeSubstance VARCHAR(255) NOT NULL" +
                ")");
        System.out.println("complete.");
    }
    private void buildMedicationtable() throws SQLException{
        System.out.println("Building Medication table...");
        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Medication (" +
                "ID INTEGER PRIMARY KEY," +
                "PatientID INT," +
                "DrugID INT," +
                "Dosis DOUBLE," +
                "TimesPerDay INT," +
                "FOREIGN KEY (PatientID) REFERENCES Patient(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (DrugID) REFERENCES Drug(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");
        System.out.println("complete.");
    }
    private void buildAppointmentTable() throws SQLException{
        System.out.println("Building Appointment table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Appointment (" +
                "ID INTEGER PRIMARY KEY," +
                "PatientID int," +
                "PhysicianID int," +
                "TimeAndDate text," +
                "FOREIGN KEY (PatientID) REFERENCES User (UserID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (PhysicianID) REFERENCES User (UserID) ON DELETE CASCADE ON UPDATE CASCADE" +
                " )");

        System.out.println("complete.");
    }
    private void buildRatingTable() throws SQLException{
        System.out.println("Building Rating table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Rating (" +
                "ID INTEGER PRIMARY KEY," +
                "PhysicianID INT," +
                "PatientID INT," +
                "treatmentRating INT NOT NULL," +
                "severeMisstreatment BOOLEAN NOT NULL," +
                "severeMisstreatmentExplain VARCHAR(255) NOT NULL DEFAULT('No misstreatment')," +
                "equipment INT NOT NULL," +
                "explanationQuality INT NOT NULL," +
                "sympathy INT NOT NULL," +
                "timeUntilAppointment INT NOT NULL," +
                "timeUntilCalledOn INT NOT NULL," +
                "handicapFriendly INT NOT NULL," +
                "availabilityPhone INT NOT NULL," +
                "openingHours INT NOT NULL," +
                "individualRating VARCHAR(255) NOT NULL DEFAULT('No individual rating')," +
                "FOREIGN KEY (PatientID) REFERENCES User(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (treatmentRating) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (equipment) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (explanationQuality) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (sympathy) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (timeUntilAppointment) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (timeUntilCalledOn) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (handicapFriendly) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (availabilityPhone) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (openingHours) REFERENCES Thumb(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY (PhysicianID) REFERENCES User (UserID) ON DELETE CASCADE ON UPDATE CASCADE" +
                " )");

        System.out.println("complete.");
    }
    private void buildThumbTable() throws SQLException{
        System.out.println("Building Thumb table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Thumb (" +
                "ID INTEGER PRIMARY KEY," +
                "rating VARCHAR(255) UNIQUE NOT NULL" +
                " )");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Thumb (" +
                "rating)" +
                "VALUES (?);");

        preparedStatement.setString(1, "Up");
        preparedStatement.execute();
        preparedStatement.setString(1, "Down");
        preparedStatement.execute();
        preparedStatement.setString(1, "Nothing");
        preparedStatement.execute();

        System.out.println("complete.");
    }
    private void buildTagTable() throws SQLException{
        System.out.println("Building Tag table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Tag (" +
                "ID INTEGER PRIMARY KEY," +
                "name VARCHAR(255) UNIQUE NOT NULL ," +
                "counter NOT NULL DEFAULT(1)" +
                ")");

        System.out.println("complete.");
    }
    private void buildRatingTagTable() throws SQLException{
        System.out.println("Building RatingTag table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE RatingTag (" +
                "ID INTEGER PRIMARY KEY," +
                "RatingID INT NOT NULL," +
                "TagID INT NOT NULL," +
                "FOREIGN KEY (RatingID) REFERENCES Rating(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (TagID) REFERENCES Tag(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                " )");

        System.out.println("complete.");
    }
    private void buildTransferOrderTable() throws SQLException{
        System.out.println("Building Transferorder table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Transferorder (" +
                "ID INTEGER PRIMARY KEY," +
                "PatientID INT NOT NULL," +
                "PhysicianID INT NOT NULL," +
                "SpecializationID INT NOT NULL," +
                "FOREIGN KEY (PatientID) REFERENCES Patient(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (PhysicianID) REFERENCES Physician(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (SpecializationID) REFERENCES Specialization(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                " )");

        System.out.println("complete.");
    }














}



