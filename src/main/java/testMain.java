import com.fasterxml.jackson.databind.JsonNode;



import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class testMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        //test database!
            //test patientfunctions
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
        try{
            Physician physician = databaseconnection.getPhysician("Doctor");
            Physician test = new Physician("dtestmail", "Bruce", "Banner", "Berlin", "Hauptstraße", "2", "60001", "110", "Dr.", "abcd", physician.getSpecialization());
            databaseconnection.addUser(test);
            Physician test2 = databaseconnection.getPhysician(test.getEmailAddress());
            test2.setLastName("Penis");
            databaseconnection.updateUser(test2); //SQLERROR
            test2 = databaseconnection.getPhysician(test2.getEmailAddress());
            System.out.println(test2.getLastName());

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
            Appointment[] test = databaseconnection.getAppointment("Doctor");
            System.out.println(test[0].getPatient().getLastName());
            System.out.println(test[0].getPhysician().getSpecialization()[0]);
            System.out.println(test[1].getDate());

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        try{
           Appointment appointment = new Appointment(databaseconnection.getPatient(1), databaseconnection.getPhysician(2), LocalDateTime.of(2021,02,01,18,30));
           databaseconnection.addAppointment(appointment);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //END test database




        //Test for distance --> returns km from two addresses

        System.out.println(LatLong.finalDistance("Ben-Gurion-Ring 48c", "Wetterauweg 30"));






    }
}
