package forms.managementPages;


import forms.*;
import forms.classPages.ClassList;
import forms.clientPages.ClientList;
import forms.clubPages.ClubList;
import forms.employeePages.EmployeeList;

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
    private JPanel topToolBarPanel;
    private JButton mainButton;
    private JButton clientsButton;
    private JButton employeesButton;
    private JButton clubsButton;
    private JButton classesButton;
    private JButton managementButton;
    private JPanel managementPageMainPanel;

    public ManagementPage() {
        setTitle("Management Page");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(managementPageMainPanel);
        clientsButton.addActionListener(e -> {
            ClientList clientList = new ClientList();
            clientList.setSize(1, 1);
            setContentPane(clientList.getContentPane());
            clientList.setVisible(true);
            clientList.setContentPane(clientList.getContentPane());
            this.dispose();
        });
        mainButton.addActionListener(e -> {
            MainPage mainPage = new MainPage();
            mainPage.setSize(1, 1);
            setContentPane(mainPage.getContentPane());
            mainPage.setVisible(true);
            mainPage.setContentPane(mainPage.getContentPane());
            this.dispose();
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

            clientsButton.setText("Already Here :)");
        });
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
