package forms.managementPages.Clubs.add;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.managementPages.Clubs.list.ManagementClubCostsPage;
import forms.managementPages.ManagementPage;
import models.Klub;
import models.Manager;
import models.Przychod;
import models.RozliczenieMiesieczne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ManagementClubCostsAddPage extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
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
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private final SwingUiChanger swingUiChanger= new SwingUiChanger();
    private List<Klub> klubList= new ArrayList<>();
    private Manager authManager;

    public ManagementClubCostsAddPage(Manager manager) {
        setTitle("Management Clubs Costs Add Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        authManager=manager;
        emailLabel.setText("Welcome "+manager.getImie());
        populateComboBoxes();
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage(manager)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(manager)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        saveButton.addActionListener(x -> {
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
                        session.save(new RozliczenieMiesieczne(1L,klubList.get(clubComboBox.getSelectedIndex()), LocalDate.of(Integer.parseInt(yearComboBox.getSelectedItem().toString()),Integer.parseInt( monthComboBox.getSelectedItem().toString()),1), (Manager) session.createQuery("from manager where id=1")));
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
                    swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(manager,new ArrayList<>()));
        });
    }

    private void populateComboBoxes() {
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

             klubList = session.createQuery("from klub ").list();
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
