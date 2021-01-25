import javax.xml.crypto.Data;
import java.sql.SQLException;

public class testMain {

    public static void main(String[] args) {

        Login.loginFrame();

        Databaseconnection databaseconnection = new Databaseconnection();
       try{
           Patient test = databaseconnection.getPatient("asd");
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




    }
}
