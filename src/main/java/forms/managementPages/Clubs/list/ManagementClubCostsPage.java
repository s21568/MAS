package forms.managementPages.Clubs.list;

import forms.MainPage;
import forms.unilities.SwingUiChanger;
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
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private JButton printButton;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private final List<Klub> klubList = new ArrayList<>();
    private List<RozliczenieMiesieczne> rozliczenieMiesieczneList = new ArrayList<>();
    private final List<RozliczenieMiesieczne> selectedRozliczenieMiesieczneList = new ArrayList<>();

    public ManagementClubCostsPage(Manager manager, List<Klub> klubList) {
        setTitle("Management Clubs Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        emailLabel.setText("Welcome " + manager.getImie());
        this.klubList.addAll(klubList);
        clubsMonthlyTableList.setModel(populateClientTableModel());
        clubsMonthlyTableList.setColumnSelectionAllowed(true);
        clubsMonthlyTableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubsPage(manager)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(manager)));
        incomesButton.addActionListener(e -> {
            int[] selectedRows = clubsMonthlyTableList.getSelectedRows();
            if (clubsMonthlyTableList.getSelectedRowCount() > 0) {
                System.out.println(clubsMonthlyTableList.getSelectedRowCount());
                for (int x : selectedRows) {
                    selectedRozliczenieMiesieczneList.add(this.rozliczenieMiesieczneList.get(x));
                }
                swingUiChanger.changeSwingUi(this, new ManagementClubCostsIncomesPage(manager, selectedRozliczenieMiesieczneList,klubList));
            }
        });
        expensesButton.addActionListener(e -> {
            int[] selectedRows = clubsMonthlyTableList.getSelectedRows();
            if (clubsMonthlyTableList.getSelectedRowCount() > 0) {
                for (int x : selectedRows) {
                    selectedRozliczenieMiesieczneList.add(this.rozliczenieMiesieczneList.get(x));
                }
                swingUiChanger.changeSwingUi(this, new ManagementClubCostsExpensesPage(manager, selectedRozliczenieMiesieczneList,klubList));
            }
        });
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
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
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
            model.addColumn("miesiąc pokrycia");
            model.addColumn("data Dodania");
            model.addColumn("Manager autoryzujący");
            model.addColumn("id Klubu");
            rozliczenieMiesieczneList = session.createQuery("from rozliczenie_miesieczne").list();
            for (RozliczenieMiesieczne x : rozliczenieMiesieczneList) {
                for (Klub klub : klubList) {
                    if (x.getIdKlubu().getId() == klub.getId()) {
                        model.addRow(x.getFullInfo());
                    }
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
