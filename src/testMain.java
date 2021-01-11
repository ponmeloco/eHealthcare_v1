import java.sql.ResultSet;

public class testMain {

    public static void main(String[] args) {
        patient achim = new patient(1,"Archy","archy@xhamsters.xxx","Achim",
                "Glaesmann", "Frankfurt","Ben-Gurion-Ring","48c","60489",
                "01758465423","Dr.");

        Databaseconnection dbcnct = new Databaseconnection();
        ResultSet dbresult;

        try{
            dbresult = dbcnct.displayUsers();
        }catch(Exception e){
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println("fuck");
        }


    }
}
