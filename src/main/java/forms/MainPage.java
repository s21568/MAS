package forms;

import forms.classPages.ClassList;
import forms.clientPages.ClientList;
import forms.clubPages.ClubList;
import forms.managementPages.ManagementPage;
import forms.unilities.SwingUiChanger;
import models.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private Manager authmanager;

    public MainPage() {
        setInitialParametersAndActions();
    }

    public MainPage(Manager manager) {
        authmanager=manager;
        setInitialParametersAndActions();
    }

    private void setInitialParametersAndActions(){
        setTitle("Main Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(getContentPane());
        clientsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
        mainButton.addActionListener(e -> mainButton.setText("Already Here :)"));
        employeesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
        clubsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClubList()));
        classesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClassList()));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authmanager)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        if(authmanager!=null){
            emailLabel.setText("Welcome "+authmanager.getImie());
            emaliField.setVisible(false);
            passwordLabel.setVisible(false);
            passwordField.setVisible(false);
            managementButton.setVisible(true);
        }
        LogIn.addActionListener(x -> {
            if (emaliField.getText() != null) {
                emailLabel.setBackground(Color.BLACK);
                List<Manager> manager = new ArrayList<>();
                StandardServiceRegistry registry = null;
                SessionFactory sessionFactory = null;
                try {
                    registry = new StandardServiceRegistryBuilder()
                            .configure()
                            .build();
                    sessionFactory = new MetadataSources(registry)
                            .buildMetadata()
                            .buildSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();
                    manager = session.createQuery("from manager where email=:email").setParameter("email", emaliField.getText()).list();
                    session.getTransaction().commit();
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    StandardServiceRegistryBuilder.destroy(registry);
                } finally {
                    if (sessionFactory != null) {
                        sessionFactory.close();
                    }
                }
                if (!manager.isEmpty()) {
                    authmanager = manager.get(0);
                    emailLabel.setText("Welcome "+authmanager.getImie());
                    emaliField.setVisible(false);
                    passwordLabel.setVisible(false);
                    passwordField.setVisible(false);
                    managementButton.setVisible(true);
                }else {
                    emailLabel.setForeground(Color.RED);
                    emailLabel.setText("No such Manager");
                }
            } else {
                emailLabel.setBackground(Color.RED);
            }
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
