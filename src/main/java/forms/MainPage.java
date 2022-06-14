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
    private final SwingUiChanger swingUiChanger= new SwingUiChanger();

    public MainPage() {
        setTitle("Main Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(getContentPane());
        clientsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
        mainButton.addActionListener(e -> mainButton.setText("Already Here :)"));
        employeesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
        clubsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClubList()));
        classesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClassList()));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage()));
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
