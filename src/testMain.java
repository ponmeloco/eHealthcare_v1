import javax.xml.crypto.Data;
import java.sql.SQLException;

public class testMain {

    public static void main(String[] args) {
        Databaseconnection databaseconnection = new Databaseconnection();
        try {
            Physician test = databaseconnection.getPhysician("Doctor");
            System.out.println(test.getEmailAddress());
            Patient test2 = databaseconnection.getPatient("Patient");
            System.out.println(test2.getEmailAddress());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Login.loginFrame();
        MainInterface main = new MainInterface(new Physician("test@mail.de", "Sascha", "Bichler"));
        //main.setVisible(true);
        Patient Achim = new Patient();

    }
}
