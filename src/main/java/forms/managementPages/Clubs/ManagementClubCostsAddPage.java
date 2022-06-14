package forms.managementPages.Clubs;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.clientPages.ClientList;
import forms.managementPages.ManagementPage;
import models.Klub;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagementClubCostsAddPage extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
    private JPanel LogInPanel;
    private JButton LogIn;
    private JButton LogOut;
    private JPasswordField passwordField;
    private JTextField emaliField;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton managementButton;
    private JButton saveButton;
    private JComboBox clubComboBox;
    private JComboBox yearComboBox;
    private JComboBox monthComboBox;
    private JLabel date;
    private JLabel club;
    private JLabel month;
    private JLabel year;
    private final SwingUiChanger swingUiChanger= new SwingUiChanger();

    public ManagementClubCostsAddPage() {
        setTitle("Management Clubs Costs Add Page");
//        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
        setContentPane(managementPageMainPanel);
        populateComboBoxes();
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage()));

        saveButton.addActionListener(e -> {

            swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage());
        });
    }

    private void populateComboBoxes() {
        List<String> months = new ArrayList<>();
        List<String> years = new ArrayList<>();
        List<String> clubs = new ArrayList<>();
        for (int x = 1; x < 13; x++) {
            if (x < 10) {
                monthComboBox.addItem("0" + x);
            } else {
                monthComboBox.addItem(x);
            }
        }
        for(int x = LocalDateTime.now().getYear()-30;x<=LocalDateTime.now().getYear();x++){
            yearComboBox.addItem(x);
        }
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

            List<Klub> klubList = session.createQuery("from klub ").list();
            for (Klub x : klubList) {
                clubComboBox.addItem(x.getAdres().pokazInfo());
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
    }

    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Costs Add Page");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(managementPageMainPanel);
        return managementPageMainPanel;
    }

}
