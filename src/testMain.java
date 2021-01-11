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

            while(dbresult.next()){
                System.out.println(dbresult.getString(1) + " " + dbresult.getString(2));
            }
        }catch(Exception e){

            System.err.println(e.getMessage());
            System.err.println("fuck");
        }


    }
}
