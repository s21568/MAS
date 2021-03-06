package forms.clubPages;

import forms.unilities.LogInAuth;
import forms.unilities.SwingUiChanger;
import forms.classPages.ClassList;
import forms.clientPages.ClientList;
import forms.MainPage;
import forms.employeePages.EmployeeList;
import forms.managementPages.ManagementPage;
import models.Klub;
import models.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClubList extends JFrame {
    private JScrollPane scrollPanel;
    private JTable clientTableList;
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
    private JPanel clubListMainPanel;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private Manager authmanager;

    public ClubList() {
        setContentPane(clubListMainPanel);
        clientsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        employeesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new EmployeeList()));
        clubsButton.addActionListener(e -> clubsButton.setText("Already Here :)"));
        classesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClassList()));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authmanager)));
        LogIn.addActionListener(x -> {
            if (emaliField.getText() != null) {
                emailLabel.setBackground(Color.BLACK);
                List<Manager> manager = new LogInAuth().chceckCredentials(emaliField.getText(),passwordField.getText());
                if (!manager.isEmpty()) {
                    authmanager = manager.get(0);
                    emailLabel.setText("Welcome " + authmanager.getImie());
                    emaliField.setVisible(false);
                    passwordLabel.setVisible(false);
                    passwordField.setVisible(false);
                    managementButton.setVisible(true);
                } else {
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
        setTitle("Club List");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(clubListMainPanel);
        clientTableList.setModel(populateClientTableModel());
        return clubListMainPanel;
    }

    public DefaultTableModel populateClientTableModel() {
        DefaultTableModel model = new DefaultTableModel();
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
            model.addColumn("Id");
            model.addColumn("Opening Date");
            model.addColumn("Opening Hour");
            model.addColumn("Closing Hour");
            model.addColumn("Address");

            List<Klub> klubList = session.createQuery("from klub ").list();
            for (Klub x : klubList) {
                model.addRow(x.getFullInfo());
            }
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
        return model;
    }
}
