package forms.employeePages;

import forms.classPages.ClassList;
import forms.clientPages.ClientList;
import forms.clubPages.ClubList;
import forms.MainPage;
import forms.managementPages.ManagementPage;
import models.Pracownik;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeeList extends JFrame {
    private JPanel employeListMainPanel;
    private JScrollPane scrollPanel;
    private JTable employeeTableList;
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

    public EmployeeList() {
        setTitle("Employee List");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(employeListMainPanel);
        employeeTableList.setModel(populateEmployeeTableModel());
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
            clientsButton.setText("Already Here :)");
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
        setTitle("Employee List");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(employeListMainPanel);
        employeeTableList.setModel(populateEmployeeTableModel());
        return employeListMainPanel;
    }

    public DefaultTableModel populateEmployeeTableModel() {
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
            model.addColumn("Adres");
            model.addColumn("DataZatrudnienia");
            model.addColumn("Pensja");

            List<Pracownik> pracownikList = session.createQuery("from pracownik").list();
            for (Pracownik x : pracownikList) {
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