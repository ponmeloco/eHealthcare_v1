import javax.xml.crypto.Data;
import java.sql.SQLException;

public class testMain {

    public static void main(String[] args) {
        Databaseconnection databaseconnection = new Databaseconnection();
        try{
            Patient patient = databaseconnection.getPatient("Patient");
            Patient test = new Patient("testmail", "Tessy",  "Test", "Berlin","Hauptstraße",  "1",  "60001",  "112",  "Dr.", "_password",
                    "2020-01-01",  "AOK", databaseconnection.getSymptoms("Patient"), databaseconnection.getMedication("Patient"), 12);


                databaseconnection.addUser(test);
                Patient test2 = databaseconnection.getPatient("testmail");
                test2.setLastName("Penis");
                databaseconnection.updateUser(test2); //this still needs work
                test2 = databaseconnection.getPatient("testmail");
                System.out.println(test2.getLastName());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Login.loginFrame();
        MainInterface main = new MainInterface(new Physician("test@mail.de", "Sascha", "Bichler"));
        //main.setVisible(true);
        Patient Achim = new Patient();

    }
}
