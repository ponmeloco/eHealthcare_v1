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

    public String getUserPw(String email) throws SQLException, ClassNotFoundException {
        if(connection == null){
            connect();
        }
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT password FROM User WHERE emailAddress ='" + email + "';").getString(1);
    }

    public void addUser(Patient patient) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connect();
        }
        Statement statement = connection.createStatement();
        ResultSet checkIfNew = statement.executeQuery("SELECT * FROM USER WHERE emailAddress='" + patient.getEmailAddress() + "')");

        if (!checkIfNew.next()) {
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
            preparedStatement.setString(10, patient.getTitle());
            preparedStatement.execute();

            ResultSet res = statement.executeQuery("SELECT ID FROM User WHERE emailAddress='" + patient.getEmailAddress() + "';");
            int ID = res.getInt(1);
            statement.execute("INSERT INTO Patient VALUES (" + ID + ")");
        }else{
            throw new SQLException("User already registered.");
        }
    }
    public void addUser(Physician physician) throws SQLException, ClassNotFoundException{
        if(connection == null){
            connect();
        }
        int ID;
        int specID;
        Statement statement = connection.createStatement();
        ResultSet checkIfNew = statement.executeQuery("SELECT * FROM USER WHERE emailAddress='"+physician.getEmailAddress()+"')");

        if(!checkIfNew.next()) {
            /*Insert into user table*/
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

            /* Insert Physician and SpezializationPhysisician  table*/

            ResultSet res = statement.executeQuery("SELECT ID FROM User WHERE emailAddress='" + physician.getEmailAddress() + "';");
            ID = res.getInt(1);
            statement.execute("INSERT INTO Physician VALUES (" + ID + ")");
            res = statement.executeQuery("SELECT ID FROM Specialization WHERE Specialization='" + physician.getSpecialization()[1] + "';");
            specID = res.getInt(1);
            statement.execute("INSERT INTO SpecializationPhysician(PhysicianID, SpecializationID) VALUES (" + ID + "," + specID + ");");
        }else{
            throw new SQLException("User already registered.");
         }
    }
    public void addUser(Admin admin) throws SQLException, ClassNotFoundException {
        if (connection == null) {
            connect();
        }
        Statement statement = connection.createStatement();
        ResultSet checkIfNew = statement.executeQuery("SELECT * FROM USER WHERE emailAddress='" + admin.getEmailAddress() + "')");

        if (!checkIfNew.next()) {
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
            preparedStatement.setString(10, admin.getTitle());

            preparedStatement.execute();


            ResultSet res = statement.executeQuery("SELECT ID FROM User WHERE emailAddress='" + admin.getEmailAddress() + "';");
            int ID = res.getInt(1);
            statement.execute("INSERT INTO Admin VALUES (" + ID + ")");
        }
    }

    public Patient      getPatient(String email) throws SQLException, ClassNotFoundException{
        if(connection == null){
            connect();
        }

        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT u.*,p.DateOfBirth,p.weight,i.name FROM User AS u JOIN (Patient AS p JOIN Insurance as i ON p.InsuranceID = i.ID) ON u.ID = p.ID WHERE emailAddress ='" + email + "';");

        String patientID =          res.getString(1);
        String pwhash =             res.getString(3);
        String firstName =          res.getString(4);
        String lastName =           res.getString(5);
        String city =               res.getString(6);
        String street =             res.getString(7);
        String houseNumber =        res.getString(8);
        String postalCode =         res.getString(9);
        String phoneNumber =        res.getString(10);
        String title =              res.getString(11);
        String dateOfBirth =        res.getString(12);
        int weight =                res.getInt(13);
        String insurancename =      res.getString(14);
        Medication[] medications =  getMedication(email);
        Symptom[] symptoms =        getSymptoms(email);


        return new Patient(email,firstName,lastName,city,street,houseNumber,postalCode,
                phoneNumber, title, pwhash, dateOfBirth, insurancename, symptoms, medications, weight);
    }
    public Physician    getPhysician(String email) throws SQLException, ClassNotFoundException{
        if(connection == null){
            connect();
        }
        Statement statement = connection.createStatement();
        Statement statement2 = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT User.*, Physician.ID FROM (User JOIN Physician ON User.ID = Physician.ID) WHERE emailAddress ='" + email + "';");
        int PhysicianID;

        if (res.next()) {
            PhysicianID = res.getInt(1);
            String pwhash = res.getString(3);
            String firstName = res.getString(4);
            String lastName = res.getString(5);
            String city = res.getString(6);
            String street = res.getString(7);
            String houseNumber = res.getString(8);
            String postalCode = res.getString(9);
            String phoneNumber = res.getString(10);
            String title = res.getString(11);
            String[] specialization = getSpecialization(PhysicianID);


            return new Physician(email, firstName, lastName, city, street, houseNumber, postalCode,
                    phoneNumber, title, pwhash, specialization);

        }else{
            throw new SQLException("User not found");
        }
    }
    public Admin        getAdmin(String email) throws SQLException, ClassNotFoundException{
        if(connection == null){
            connect();
        }
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT User.* FROM User WHERE emailAddress ='" + email + "';");

        String pwhash = res.getString(3);
        String firstName = res.getString(4);
        String lastName = res.getString(5);
        String city = res.getString(6);
        String street = res.getString(7);
        String houseNumber = res.getString(8);
        String postalCode = res.getString(9);
        String phoneNumber = res.getString(10);
        String title = res.getString(11);

        return new Admin(email,firstName,lastName,city,street,houseNumber,postalCode,
                phoneNumber, title, pwhash);
    }

     private Symptom[]       getSymptoms(String email) throws SQLException,ClassNotFoundException{
         if(connection == null){
             connect();
         }
        int countOfSymptoms = 0;
         Statement statement = connection.createStatement();
         ResultSet res = statement.executeQuery("SELECT s.name,s.description,sv.severeness FROM (Symptom AS s JOIN SymptomPatient AS sp " +
                 "ON (SELECT sp.SymptomID FROM SymptomPatient WHERE sp.PatientID = 1) = s.ID) " +
                 "JOIN Severeness AS sv " +
                 "ON(SELECT sp.SeverenessID FROM SymptomPatient as sp WHERE sp.PatientID = 1)=sv.ID");

         while(res.next()){
             countOfSymptoms++;
         }

         Symptom[] symptoms = new Symptom[countOfSymptoms];
         res = statement.executeQuery("SELECT s.name,s.description,sv.severeness FROM (Symptom AS s JOIN SymptomPatient AS sp " +
                 "ON (SELECT sp.SymptomID FROM SymptomPatient WHERE sp.PatientID = 1) = s.ID) " +
                 "JOIN Severeness AS sv " +
                 "ON(SELECT sp.SeverenessID FROM SymptomPatient as sp WHERE sp.PatientID = 1)=sv.ID");

         for (int i = 0; res.next(); i++){
             symptoms[i] = new Symptom(res.getString(1), res.getString(2), res.getString(3));
         }
         return symptoms;
     }
     private Medication[]    getMedication(String email) throws SQLException,ClassNotFoundException{
         if(connection == null){
             connect();
         }
         int countOfMedication = 0;
         int patientID;
         Statement statement = connection.createStatement();
         patientID = statement.executeQuery("SELECT ID FROM User Where emailAddress='"+email+"';" ).getInt(1);
         ResultSet res = statement.executeQuery("SELECT Dosis, TimesPerDay, DrugID FROM Medication Where PatientID="+patientID+";");


         while(res.next()){
             countOfMedication++;
         }

         Medication[] medication = new Medication[countOfMedication];
         res = statement.executeQuery("SELECT DrugID, Dosis, TimesPerDay FROM Medication Where PatientID="+patientID+";");

         for (int i = 0; res.next(); i++){
             medication[i] = new Medication(getDrug(res.getInt(1)), res.getDouble(2), res.getInt(3));
         }
         return medication;
     }
     private String[]        getSpecialization(int PhysicianID) throws SQLException,ClassNotFoundException{
         if(connection == null){
             connect();
         }
         int countOfSpecializations = 0;
         Statement statement = connection.createStatement();
         Statement statement2 = connection.createStatement();
         ResultSet res = statement.executeQuery("SELECT * FROM SpecializationPhysician WHERE PhysicianID='" + PhysicianID + "';");
         while (res.next()) {
             countOfSpecializations++;
         }

         String[] specialization = new String[countOfSpecializations];
         if (countOfSpecializations > 0) {
             res = statement.executeQuery("SELECT * FROM SpecializationPhysician WHERE PhysicianID='" + PhysicianID + "';");

             for (int i = 0;res.next();i++) {
                 specialization[i] = statement2.executeQuery("SELECT Specialization FROM Specialization WHERE ID='" +res.getInt(3)+ "';").getString(1);
             }

         }

         return specialization;
     }
     public Drug            getDrug(String drugName)throws SQLException,ClassNotFoundException{
         if(connection == null){
             connect();
         }

         Statement statement = connection.createStatement();
         ResultSet res = statement.executeQuery("SELECT Drug.name, Drug.description FROM Drug Where name ='"+ drugName +"';");
         return new Drug(res.getString(1),res.getString(2));
     }
     public Drug            getDrug(int drugID) throws SQLException,ClassNotFoundException{
         if(connection == null){
             connect();
         }

         Statement statement = connection.createStatement();
         ResultSet res = statement.executeQuery("SELECT Drug.name, Drug.description FROM Drug Where ID ="+ drugID +";");
         return new Drug(res.getString(1),res.getString(2));

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
                buildInsuranceTypeTable();
                buildInsuranceTable();
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

                System.out.println("Database build. \n\n\n");

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

        preparedStatement.setString(1, "Patient");
        preparedStatement.setString(2, User.hashPassword("asd"));
        preparedStatement.setString(3, "Test");
        preparedStatement.setString(4, "Patient");
        preparedStatement.setString(5, "Frankfurt");
        preparedStatement.setString(6, "Ben-Gurion-Ring");
        preparedStatement.setString(7, "48C");
        preparedStatement.setString(8, "60437");
        preparedStatement.setString(9, "015738340183");
        preparedStatement.setString(10,"hobo");
        preparedStatement.execute();

        preparedStatement.setString(1, "Doctor");
        preparedStatement.setString(2, User.hashPassword("asd"));
        preparedStatement.setString(3, "Test");
        preparedStatement.setString(4, "Doktor");
        preparedStatement.setString(5, "Frankfurt");
        preparedStatement.setString(6, "Ben-Gurion-Ring");
        preparedStatement.setString(7, "48C");
        preparedStatement.setString(8, "60437");
        preparedStatement.setString(9, "015738340183");
        preparedStatement.setString(10,"Dr.");
        preparedStatement.execute();

        System.out.println("complete.");
    }
    private void buildInsuranceTypeTable() throws SQLException{
        System.out.println("Building InsuranceType table...");
        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE InsuranceType (" +
                "ID INTEGER PRIMARY KEY," +
                "Type VARCHAR(255) NOT NULL UNIQUE" +
                ")");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO InsuranceType (" +
                "Type) " +
                "VALUES (?);");

        preparedStatement.setString(1, "public");
        preparedStatement.execute();
        preparedStatement.setString(1, "private");
        preparedStatement.execute();


        System.out.println("complete.");
    }
    private void buildInsuranceTable() throws SQLException{
        System.out.println("Building Insurance table...");
        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Insurance (" +
                "ID INTEGER PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL UNIQUE," +
                "insuranceTypeID INT NOT NULL," +
                "FOREIGN KEY(insuranceTypeID) REFERENCES InsuranceType(ID) ON DELETE RESTRICT ON UPDATE CASCADE" +
                ")");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Insurance (" +
                "name," +
                "insuranceTypeID)" +
                "VALUES (?,?);");

        preparedStatement.setString(1, "AOK");
        preparedStatement.setInt(2, 1);
        preparedStatement.execute();
        preparedStatement.setString(1, "Techniker Krankenkasse");
        preparedStatement.setInt(2, 1);
        preparedStatement.execute();
        preparedStatement.setString(1, "Allianz");
        preparedStatement.setInt(2, 2);
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
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Physician (" +
                "ID) VALUES (?)");

        /* Insert Test Physician */
        preparedStatement.setString(1, "2");
        preparedStatement.execute();

        System.out.println("complete.");
    }
    private void buildPatientTable() throws SQLException {
        System.out.println("Building Patient table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Patient (" +
                "ID int NOT NULL," +
                "insuranceID int NOT NULL," +
                "dateOfBirth DATE NOT NULL," +
                "weight INT NOT NULL," +
                "PRIMARY KEY(ID), " +
                "FOREIGN KEY(ID) REFERENCES User(ID) ON DELETE RESTRICT ON UPDATE CASCADE," +
                "FOREIGN KEY(insuranceID) REFERENCES Insurance(ID) ON DELETE RESTRICT ON UPDATE CASCADE" +
                ")");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Patient (" +
               "ID, dateOfBirth,weight, insuranceID) VALUES (?,?,?,?)");

        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "2020-01-15");
        preparedStatement.setInt(3, 93);
        preparedStatement.setInt(4, 1);
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
        //Populating Table
        {/* Insert Specialization */
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Specialization (" +
                    "Specialization) VALUES (?)");
            preparedStatement.setString(1, "Allgemeinmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Anästhesiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Anatomie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Arbeitsmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Augenheilkunde");
            preparedStatement.execute();
            preparedStatement.setString(1, "Biochemie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Chirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Chirurgie_Allgemeine Chirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Forensische Psychiatrie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Frauenheilkunde und Geburtshilfe");
            preparedStatement.execute();
            preparedStatement.setString(1, "Gefäßchirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Gynäkologische Onkologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Hals-Nasen-Ohrenheilkunde");
            preparedStatement.execute();
            preparedStatement.setString(1, "Haut- und Geschlechtskrankheiten");
            preparedStatement.execute();
            preparedStatement.setString(1, "Herzchirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Humangenetik");
            preparedStatement.execute();
            preparedStatement.setString(1, "Hygiene und Umweltmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin ");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin und Endokrinologie und Diabetologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin und Gastroenterologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin und Hämatologie und Onkologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin und Kardiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin und Nephrologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin und Pneumologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin und Rheumatologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Kinder- und Jugendmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Kinder- und Jugendpsychiatrie und -psychotherapie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Kinderchirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Kinderradiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Klinische Pharmakologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Laboratoriumsmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Mikrobiologie, Virologie und Infektionsepidemiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Mund-Kiefer-Gesichtschirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Neurochirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Neurologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Neuroradiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Nuklearmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Orthopädie und Unfallchirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Pathologie ");
            preparedStatement.execute();
            preparedStatement.setString(1, "Pharmakologie und Toxikologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Phoniatrie und Pädaudiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Physikalische und Rehabilitative Medizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Physiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Plastische, Rekonstruktive und Ästhetische Chirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Psychiatrie und Psychotherapie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Psychosomatische Medizin und Psychotherapie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Radiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Rechtsmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "SP Gynäkologische Endokrinologie und Reproduktionsmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "SP Kinder-Hämatologie und -Onkologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "SP Kinder-Kardiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "SP Neonatologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "SP Neuropädiatrie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Spezielle Geburtshilfe und Perinatalmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Strahlentherapie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Thoraxchirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Transfusionsmedizin");
            preparedStatement.execute();
            preparedStatement.setString(1, "Urologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Viszeralchirurgie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Innere Medizin und Angiologie");
            preparedStatement.execute();
            preparedStatement.setString(1, "Öffentliches Gesundheitswesen");
            preparedStatement.execute();


        }

        System.out.println("complete.");
    }
    private void buildSpecializationPhysicianTable() throws SQLException {
        System.out.println("Building SpecializationPhysician table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE SpecializationPhysician (" +
                "ID INTEGER PRIMARY KEY," +
                "PhysicianID INT," +
                "SpecializationID INT," +
                "FOREIGN KEY (SpecializationID) REFERENCES Specialization(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (PhysicianID) REFERENCES Physician(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");

        {/* Insert Test SpecializationPhysician */
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO SpecializationPhysician(PhysicianID,SpecializationID) VALUES (?,?);");
        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 1);
        preparedStatement.execute();
        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 2);
        preparedStatement.execute();
        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 7);
        preparedStatement.execute();
        preparedStatement.setInt(1, 2);
        preparedStatement.setInt(2, 14);
        preparedStatement.execute();}

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


        {//Add Teststubs
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Symptom (" +
                    " name,description) VALUES (?,?)");

            preparedStatement.setString(1, "Cough");
            preparedStatement.setString(2, "Immune Reaction to foreign particles within the respiratory System.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Rash");
            preparedStatement.setString(2, "an area of redness and spots on a person's skin, appearing especially as a result of allergy or illness.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Fever");
            preparedStatement.setString(2, "an abnormally high body temperature, usually accompanied by shivering, headache, and in severe instances, delirium.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Chills");
            preparedStatement.setString(2, "The feeling of being cold, though not necessarily in a cold environment, often accompanied by shivering or shaking.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Shortness of breath");
            preparedStatement.setString(2, "a shortness of breath.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Breathing Difficulties");
            preparedStatement.setString(2, "difficulties breathing.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Fatigue");
            preparedStatement.setString(2, "Feeling overtired, with low energy and a strong desire to sleep that interferes with normal daily activities.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Muscle aches");
            preparedStatement.setString(2, "Dumb pain within the muscles.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Headache");
            preparedStatement.setString(2, "A painful sensation in any part of the head, ranging from sharp to dull, that may occur with other symptoms.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Loss of Taste");
            preparedStatement.setString(2, "Partial or complete loss of the sense of taste.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Loss of Smell");
            preparedStatement.setString(2, "Partial or complete loss of the sense of smell.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Sore Throat");
            preparedStatement.setString(2, "Pain or irritation in the throat that can occur with or without swallowing, often accompanies infections, such as a cold or flu.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Runny Nose");
            preparedStatement.setString(2, "Excess drainage, ranging from a clear fluid to thick mucus, from the nose and nasal passages.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Nausea");
            preparedStatement.setString(2, "Immune Reaction to foreign particles within the respiratory System.");
            preparedStatement.execute();
            preparedStatement.setString(1, "Diarrhea");
            preparedStatement.setString(2, "Immune Reaction to foreign particles within the respiratory System.");
            preparedStatement.execute();


            System.out.println("complete.");

        }
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
                "SeverenessID INT DEFAULT(3)," +
                "FOREIGN KEY (PatientID) REFERENCES Patient(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (SymptomID) REFERENCES Symptom(ID) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY (SeverenessID) REFERENCES Severeness(ID) ON DELETE CASCADE ON UPDATE CASCADE" +
                ")");
        System.out.println("complete.");

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO SymptomPatient (" +
                " PatientID,SymptomID) VALUES (?,?)");

        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 1);
        preparedStatement.execute();
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 2);
        preparedStatement.execute();
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 3);
        preparedStatement.execute();
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 17);
        preparedStatement.execute();
    }
    private void buildDrugTable() throws SQLException{
        System.out.println("Building Drug table...");

        Statement state = connection.createStatement();
        state.execute( "CREATE TABLE Drug (" +
                "ID INTEGER PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "description VARCHAR(255) NOT NULL" +
                ")");
        System.out.println("complete.");


        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Drug (" +
                " name,description) VALUES (?,?)");

        preparedStatement.setString(1, "Ibuprofen");
        preparedStatement.setString(2, "nonsteroidal anti-inflammatory drug (NSAID) class that is used for treating pain, fever, and inflammation.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Levothyroxin-Natrium");
        preparedStatement.setString(2, "Thyroid hormone thyroxine used to treat thyroid hormone deficiency, including the severe form known as myxedema coma.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Metoprolol");
        preparedStatement.setString(2, "Selective β1 receptor blocker medication. Used to treat high blood pressure, chest pain due to poor blood flow to the heart, and a number of conditions involving an abnormally fast heart rate.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Diclofenac");
        preparedStatement.setString(2, " Nonsteroidal anti-inflammatory drug (NSAID) used to treat pain and inflammatory diseases such as gout.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Ramipril");
        preparedStatement.setString(2, "ACE inhibitor used to treat high blood pressure, heart failure, diabetic kidney disease and to prevent cardiovascular disease in those at high risk.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Simvastatin");
        preparedStatement.setString(2, "lipid-lowering medication used along with exercise, diet, and weight loss to decrease elevated lipid levels also used to to prevent cardiovascular disease in those at high risk.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Metamizol-Natrium");
        preparedStatement.setString(2, "Painkiller, spasm reliever, and fever reliever that also has anti-inflammatory effects.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Omeprazol");
        preparedStatement.setString(2, "Proton-pump inhibitor used in the treatment of gastroesophageal reflux disease (GERD), peptic ulcer disease, Zollinger–Ellison syndrome and to prevent upper gastrointestinal bleeding in people who are at high risk.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Bisoprolol");
        preparedStatement.setString(2, "Beta blocker most commonly used for heart diseases especially high blood pressure, chest pain from not enough blood flow to the heart, and heart failure.");
        preparedStatement.execute();
        preparedStatement.setString(1, "Pantoprazol");
        preparedStatement.setString(2, "ATP pump inhibitor used for the treatment of stomach ulcers, short-term treatment of erosive esophagitis due to gastroesophageal reflux disease (GERD), maintenance of healing of erosive esophagitis, and pathological hypersecretory conditions.");
        preparedStatement.execute();


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

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Medication (" +
                " PatientID,DrugID, Dosis, TimesPerDay) VALUES (?,?,?,?)");

        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 1);
        preparedStatement.setDouble(3, 50.3);
        preparedStatement.setInt(4, 3);
        preparedStatement.execute();
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 2);
        preparedStatement.setDouble(3, 100.75);
        preparedStatement.setInt(4, 1);
        preparedStatement.execute();
        preparedStatement.setInt(1, 1);
        preparedStatement.setInt(2, 9);
        preparedStatement.setDouble(3, 20);
        preparedStatement.setInt(4, 15);
        preparedStatement.execute();
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



