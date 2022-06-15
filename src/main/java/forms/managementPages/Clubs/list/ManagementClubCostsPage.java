package forms.managementPages.Clubs.list;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.managementPages.Clubs.add.ManagementClubCostsAddPage;
import forms.managementPages.ManagementPage;
import models.Klub;
import models.Manager;
import models.RozliczenieMiesieczne;
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

public class ManagementClubCostsPage extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton managementButton;
    private JButton incomesButton;
    private JButton expensesButton;
    private JTable clubsMonthlyTableList;
    private JComboBox comboBox1;
    private JButton addCosts;
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private List<Klub> klubList;

    public ManagementClubCostsPage(Manager manager,List<Klub> klubList) {
        setTitle("Management Clubs Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        emailLabel.setText("Welcome "+manager.getImie());
        clubsMonthlyTableList.setModel(populateClientTableModel());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage(manager)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(manager)));
        incomesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsIncomesPage(manager,new ArrayList<>())));
        expensesButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsExpensesPage(manager,new ArrayList<>())));
        addCosts.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddPage(manager)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        this.klubList = klubList;
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

            for (Klub klub : klubList) {
                List<RozliczenieMiesieczne> rozliczenieMiesieczne = session.createQuery("from rozliczenie_miesieczne where idKlubu =:klub").setParameter("klub",klub).list();
                for (RozliczenieMiesieczne x : rozliczenieMiesieczne) {
                    model.addRow(x.getFullInfo());
                }
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
