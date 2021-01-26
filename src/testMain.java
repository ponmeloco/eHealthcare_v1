import javax.xml.crypto.Data;
import java.sql.SQLException;

public class testMain {

    public static void main(String[] args) {

        Login.loginFrame();

        Databaseconnection databaseconnection = new Databaseconnection();
       try{
           Patient test = databaseconnection.getPatient("Patient");
           System.out.println("Testing databaseconnection.getPatient ");
           System.out.println(test.getCity());
           System.out.println(test.getPhoneNUmber());
           System.out.println(test.getFirstName());
           System.out.println(test.getLastName());
           System.out.println("Done.");
       }
       catch (Exception e){
           System.out.println(e.getMessage());
       }
        try{
            Physician test = databaseconnection.getPhysician("Doctor");
            System.out.println("Testing databaseconnection.getPhysician ");
            System.out.println(test.getCity());
            System.out.println(test.getPhoneNUmber());
            System.out.println(test.getFirstName());
            System.out.println(test.getLastName());
            System.out.println(test.getSpecialization()[0]);
            System.out.println(test.getSpecialization()[3]);
            System.out.println("Done.");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
