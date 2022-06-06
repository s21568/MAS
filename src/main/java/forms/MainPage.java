package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(mainPageMainPanel);
        clientsButton.addActionListener(
                e -> {
                    ClientList clientList = new ClientList();
                    clientList.setSize(1, 1);
                    setContentPane(clientList.getContentPane());
                    clientList.setVisible(true);
                    clientList.setContentPane(clientList.getContentPane());
                    this.dispose();
                }
        );
        mainButton.addActionListener(e -> {
            mainButton.setText("Already Here :)");
        });
        employeesButton.addActionListener(e -> {

        });
        clubsButton.addActionListener(e -> {

        });
        classesButton.addActionListener(e -> {

        });
        managementButton.addActionListener(e -> {

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
