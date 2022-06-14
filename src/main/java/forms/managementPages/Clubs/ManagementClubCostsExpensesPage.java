package forms.managementPages.Clubs;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.clientPages.ClientList;
import forms.managementPages.ManagementPage;
import models.Koszt;
import models.Przychod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManagementClubCostsExpensesPage extends JFrame {
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
    private JComboBox comboBox1;
    private JTable expensesTableList;
    private JButton saveButton;
    private final SwingUiChanger swingUiChanger= new SwingUiChanger();
    public ManagementClubCostsExpensesPage() {
        setTitle("Management Clubs Expenses Page");
//        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
        setContentPane(managementPageMainPanel);
        expensesTableList.setModel(populateClientTableModel());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage()));
    }

    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Expenses Page");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(managementPageMainPanel);
        return managementPageMainPanel;
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
            model.addColumn("Wartośc");
            model.addColumn("Nazwa");
            model.addColumn("Opis");

            List<Koszt> kosztList = session.createQuery("from koszt").list();
            for (Koszt x : kosztList) {
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
