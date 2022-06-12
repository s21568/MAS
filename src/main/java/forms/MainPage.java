package forms;

import forms.classPages.ClassList;
import forms.clientPages.ClientList;
import forms.clubPages.ClubList;
import forms.employeePages.EmployeeList;
import forms.managementPages.ManagementPage;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    private JPanel topPanel;
    private JPanel LogInPanel;
    private JButton LogIn;
    private JButton LogOut;
    private JPasswordField passwordField;
    private JTextField emaliField;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JPanel mainPageMainPanel;
    private JButton clientsButton;
    private JButton mainButton;
    private JButton employeesButton;
    private JButton clubsButton;
    private JButton classesButton;
    private JButton managementButton;
    private JScrollPane toolBoxScroll;
    private JPanel topToolBarPanel;

    public MainPage() {
        setTitle("Main Page");
//        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
        setContentPane(getContentPane());
        clientsButton.addActionListener(e -> {
            ClientList clientList = new ClientList();
            clientList.setSize(1, 1);
            setContentPane(clientList.getContentPane());
            clientList.setVisible(true);
            clientList.setContentPane(clientList.getContentPane());
            this.dispose();
        });
        mainButton.addActionListener(e -> {
            mainButton.setText("Already Here :)");
        });
        employeesButton.addActionListener(e -> {
            EmployeeList employeeList = new EmployeeList();
            employeeList.setSize(1, 1);
            setContentPane(employeeList.getContentPane());
            employeeList.setVisible(true);
            employeeList.setContentPane(employeeList.getContentPane());
            this.dispose();
        });
        clubsButton.addActionListener(e -> {
            ClubList clubList = new ClubList();
            clubList.setSize(1, 1);
            setContentPane(clubList.getContentPane());
            clubList.setVisible(true);
            clubList.setContentPane(clubList.getContentPane());
            this.dispose();
        });
        classesButton.addActionListener(e -> {
            ClassList classList = new ClassList();
            classList.setSize(1, 1);
            setContentPane(classList.getContentPane());
            classList.setVisible(true);
            classList.setContentPane(classList.getContentPane());
            this.dispose();
        });
        managementButton.addActionListener(e -> {
            ManagementPage managementPage = new ManagementPage();
            managementPage.setSize(1, 1);
            setContentPane(managementPage.getContentPane());
            managementPage.setVisible(true);
            managementPage.setContentPane(managementPage.getContentPane());
            this.dispose();
        });
    }

    @Override
    public Container getContentPane() {
        setTitle("Main Page");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(mainPageMainPanel);
        return mainPageMainPanel;
    }
}
