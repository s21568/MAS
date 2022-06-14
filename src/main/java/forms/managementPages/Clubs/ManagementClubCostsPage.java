package forms.managementPages.Clubs;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.clientPages.ClientList;
import forms.managementPages.ManagementPage;
import models.Klub;
import models.RozliczenieMiesieczne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManagementClubCostsPage extends JFrame {
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
    private JButton incomesButton;
    private JButton expensesButton;
    private JTable clubsMonthlyTableList;
    private JComboBox comboBox1;
    private JButton addCosts;
    private final SwingUiChanger swingUiChanger= new SwingUiChanger();

    public ManagementClubCostsPage() {
        setTitle("Management Clubs Page");
//        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setVisible(true);
        setContentPane(managementPageMainPanel);
        clubsMonthlyTableList.setModel(populateClientTableModel());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage()));
        incomesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsIncomesPage()));
        expensesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsExpensesPage()));
        addCosts.addActionListener(e -> {
            ManagementClubCostsAddPage mainPage = new ManagementClubCostsAddPage();
            mainPage.setSize(1, 1);
            setContentPane(mainPage.getContentPane());
            mainPage.setVisible(true);
            mainPage.setContentPane(mainPage.getContentPane());
            this.dispose();
        });
    }

    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Costs Page");
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
            model.addColumn("suma Kosztów");
            model.addColumn("suma Przychodów");
            model.addColumn("data Dodania");
            model.addColumn("id Managera");
            model.addColumn("id Klubu");

            List<RozliczenieMiesieczne> rozliczenieMiesieczne = session.createQuery("from rozliczenie_miesieczne ").list();
            for (RozliczenieMiesieczne x : rozliczenieMiesieczne) {
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
