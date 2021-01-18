import java.awt.*;
import java.awt.TrayIcon.MessageType;

class WindowsNotification {

    public static void doWindowsNotification(String caption, String text) throws AWTException{

        if(SystemTray.isSupported()){
            WindowsNotification td=new WindowsNotification();
            td.displayTray(caption,text);
        }else{
            System.err.println("System tray not supported!");
        }
    }


    public void displayTray(String caption, String text) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "eHealthcare");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("eHealthcare");
        tray.add(trayIcon);

        trayIcon.displayMessage(caption, text, MessageType.INFO);
    }
}