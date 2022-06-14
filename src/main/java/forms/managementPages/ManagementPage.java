package forms.managementPages;


import forms.*;
import forms.classPages.ClassList;
import forms.clientPages.ClientList;
import forms.clubPages.ClubList;
import forms.employeePages.EmployeeList;
import forms.managementPages.Clubs.ManagementClubsPage;

import javax.swing.*;
import java.awt.*;

public class ManagementPage  extends JFrame{
    private JPanel topPanel;
    private JPanel LogInPanel;
    private JButton LogIn;
    private JButton LogOut;
    private JPasswordField passwordField;
    private JTextField emaliField;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton clientsButton;
    private JButton employeesButton;
    private JButton clubsButton;
    private JButton classesButton;
    private JButton managementButton;
    private JPanel managementPageMainPanel;
    private JButton clubsManagementButton;
    private JButton clientsManagementButton;
    private JButton employeesManagementButton;
    private final SwingUiChanger swingUiChanger= new SwingUiChanger();

    public ManagementPage() {
        setTitle("Management Page");
//        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
        setContentPane(managementPageMainPanel);
        clientsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        employeesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new EmployeeList()));
        clubsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClubList()));
        classesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClassList()));
        managementButton.addActionListener(e -> managementButton.setText("Already Here :)"));
        /*
        clientsManagementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
         */
        clubsManagementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubsPage()));
        /*
        employeesManagementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
         */
    }

    @Override
    public Container getContentPane() {
        setTitle("Management Page");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(managementPageMainPanel);
        return managementPageMainPanel;
    }
}
