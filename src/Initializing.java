import javax.swing.*;
import java.awt.*;

public class Initializing{


        public static void initializeProgramm(){

                try{
                UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
                }
                catch(UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
                        System.out.println("Can't change LookAndFeel");
                }
        }
}
