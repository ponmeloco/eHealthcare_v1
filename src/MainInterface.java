import javax.swing.*;
import java.awt.*;

public class MainInterface extends JFrame {

    private JPanel rootPanel;
    public JLabel _name;
    public JLabel _lastname;
    public JLabel _email;
    public JLabel _user;
    public JTabbedPane tabbedPane1;
    public JTable medicationTable;
    private JScrollPane scPane;


    public MainInterface(Patient _patient){

        add(rootPanel);
        setTitle("eHealthcare");
        setSize(1280,720);
        _name.setText(_patient.getFirstName());
        _lastname.setText(_patient.getLastName());
        _email.setText(_patient.getEmailAddress());
        _user.setText(_patient.getUserType());
        rootPanel.setBackground(Color.lightGray);
        tabbedPane1.removeTabAt(4);

    }

    public MainInterface(Physician _physician){

        add(rootPanel);
        setTitle("eHealthcare");
        setSize(1280,720);
        _name.setText(_physician.getFirstName());
        _lastname.setText(_physician.getLastName());
        _email.setText(_physician.getEmailAddress());
        _user.setText(_physician.getUserType());
        rootPanel.setBackground(Color.lightGray);

    }

    public MainInterface(Admin _admin){

        add(rootPanel);
        setTitle("eHealthcare");
        setSize(1280,720);
        _name.setText(_admin.getFirstName());
        _lastname.setText(_admin.getLastName());
        _email.setText(_admin.getEmailAddress());
        _user.setText(_admin.getUserType());
        rootPanel.setBackground(Color.lightGray);

    }

    private void createUIComponents() {
        Object[] columns = new Object[]{"Name Med.","Morgens","Mittags","Abends","Nachts"};
        Object[][] data = new Object[][]{{"Allopurinol 100mg","1","0","0","0"},{"Aspirin 100mg","0","1","0","0"},
                {"Bisoprolol 5mg","1","0","0","0"},{"Pantozol 40mg","1","0","0","0"}};
        medicationTable = new JTable(data, columns);
        medicationTable.getTableHeader().setReorderingAllowed(false);


    }
}


