package forms.classPages;

import forms.MainPage;
import forms.unilities.LogInAuth;
import forms.unilities.SwingUiChanger;
import forms.clientPages.ClientList;
import forms.clubPages.ClubList;
import forms.employeePages.EmployeeList;
import forms.managementPages.ManagementPage;
import models.Manager;
import models.Zajecia;
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

public class ClassList extends JFrame {
    private JScrollPane scrollPanel;
    private JTable classTableList;
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
    private JPanel classListMainPanel;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private Manager authmanager;

    public ClassList() {
        setContentPane(classListMainPanel);
        clientsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClientList()));
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        employeesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new EmployeeList()));
        clubsButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ClubList()));
        classesButton.addActionListener(e -> classesButton.setText("Already Here :)"));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authmanager)));
        LogIn.addActionListener(x -> {
            if (emaliField.getText() != null) {
                emailLabel.setBackground(Color.BLACK);
                List<Manager> manager = new LogInAuth().chceckCredentials(emaliField.getText(),passwordField.getText());
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
        setTitle("Class List");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(classListMainPanel);
        classTableList.setModel(populateClientTableModel());
        return classListMainPanel;
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
            model.addColumn("Name");
            model.addColumn("Date");
            model.addColumn("Description");
            model.addColumn("Club");
            model.addColumn("Adults only?");

            List<Zajecia> zajeciaList = session.createQuery("from zajecia ").list();
            for (Zajecia x : zajeciaList) {
                model.addRow(x.info());
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
