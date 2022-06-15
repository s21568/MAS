package forms.managementPages;


import forms.*;
import forms.classPages.ClassList;
import forms.clientPages.ClientList;
import forms.clubPages.ClubList;
import forms.employeePages.EmployeeList;
import forms.managementPages.Clubs.list.ManagementClubsPage;
import models.Manager;

import javax.swing.*;
import java.awt.*;

public class ManagementPage  extends JFrame{
    private JPanel topPanel;
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
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private final SwingUiChanger swingUiChanger= new SwingUiChanger();

    public ManagementPage(Manager manager) {
        setTitle("Management Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        emailLabel.setText("Welcome "+manager.getImie());
        clientsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage(manager)));
        employeesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new EmployeeList()));
        clubsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClubList()));
        classesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClassList()));
        managementButton.addActionListener(e -> managementButton.setText("Already Here :)"));
        /*
        clientsManagementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList(manager)));
         */
        clubsManagementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubsPage(manager)));
        /*
        employeesManagementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList(manager)));
         */
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
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
