package forms.clientPages;

import forms.classPages.ClassList;
import forms.MainPage;
import forms.clubPages.ClubList;
import forms.employeePages.EmployeeList;
import forms.managementPages.ManagementPage;
import models.Klient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClientList extends JFrame {
    private JPanel clientListMainPanel;
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

    public ClientList() {
        setTitle("Client List");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(clientListMainPanel);
        clientTableList.setModel(populateClientTableModel());
        clientsButton.addActionListener(e -> {
            clientsButton.setText("Already Here :)");
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
        setTitle("Client List");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(clientListMainPanel);
        return clientListMainPanel;
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
            model.addColumn("Imie");
            model.addColumn("Nazwisko");
            model.addColumn("DataUrodzenia");
            model.addColumn("NumerTelefonu");
            model.addColumn("Email");
            model.addColumn("IlośćSesji");
            model.addColumn("Adres");

            List<Klient> klientList = session.createQuery("from klient").list();
            for (Klient x : klientList) {
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
