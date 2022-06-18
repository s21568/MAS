package forms.managementPages.Clubs.list;

import forms.MainPage;
import forms.popUpMessages.AskToPrint;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagementClubCostsPage extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton managementButton;
    private JButton incomesButton;
    private JButton expensesButton;
    private JTable clubsMonthlyTableList;
    private JComboBox yearChooseComboBox;
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private JButton printButton;
    private JComboBox monthComboBox;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private final List<Klub> klubList = new ArrayList<>();
    private List<RozliczenieMiesieczne> rozliczenieMiesieczneList = new ArrayList<>();
    private final List<RozliczenieMiesieczne> selectedRozliczenieMiesieczneList = new ArrayList<>();
    private final Manager authmanager;

    public ManagementClubCostsPage(Manager manager, List<Klub> klubList) {
        authmanager = manager;
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
            if (clubsMonthlyTableList.getSelectedRowCount() > 0) {
                selectedRozliczenieMiesieczneList.add(rozliczenieMiesieczneList.get(clubsMonthlyTableList.getSelectedRow()));
                swingUiChanger.changeSwingUi(this, new ManagementClubCostsIncomesPage(manager, selectedRozliczenieMiesieczneList, klubList));
            }
        });
        expensesButton.addActionListener(e -> {
            if (clubsMonthlyTableList.getSelectedRowCount() > 0) {
                selectedRozliczenieMiesieczneList.add(rozliczenieMiesieczneList.get(clubsMonthlyTableList.getSelectedRow()));
                swingUiChanger.changeSwingUi(this, new ManagementClubCostsExpensesPage(manager, selectedRozliczenieMiesieczneList, klubList));
            }

        });
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        printButton.addActionListener(e -> askToPrint());
        yearChooseComboBox.addActionListener(
                event -> {
                    DefaultTableModel model = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    model.addColumn("Id");
                    model.addColumn("suma Kosztów");
                    model.addColumn("suma Przychodów");
                    model.addColumn("miesiąc pokrycia");
                    model.addColumn("data Dodania");
                    model.addColumn("Manager autoryzujący");
                    model.addColumn("id Klubu");
                    String year = Objects.requireNonNull(yearChooseComboBox.getSelectedItem()).toString();
                    for (RozliczenieMiesieczne rozliczenieMiesieczne : rozliczenieMiesieczneList) {
                        if (rozliczenieMiesieczne.getMiesiacPokrycia().toString().split("-")[0].equals(year)) {
                            model.addRow(rozliczenieMiesieczne.getFullInfo());
                        }
                    }
                    clubsMonthlyTableList.setModel(model);
                });
        monthComboBox.addActionListener(
                event -> {
                    DefaultTableModel model = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    model.addColumn("Id");
                    model.addColumn("suma Kosztów");
                    model.addColumn("suma Przychodów");
                    model.addColumn("miesiąc pokrycia");
                    model.addColumn("data Dodania");
                    model.addColumn("Manager autoryzujący");
                    model.addColumn("id Klubu");
                    String month = Objects.requireNonNull(monthComboBox.getSelectedItem()).toString();
                    for (RozliczenieMiesieczne rozliczenieMiesieczne : rozliczenieMiesieczneList) {
                        if (rozliczenieMiesieczne.getMiesiacPokrycia().toString().split("-")[1].equals(month)) {
                            model.addRow(rozliczenieMiesieczne.getFullInfo());
                        }
                    }
                    clubsMonthlyTableList.setModel(model);
                });
    }

    private AskToPrint askToPrint() {
        return new AskToPrint(clubsMonthlyTableList, "COSTS_"
                + authmanager.getImie()
                + "_" + authmanager.getNazwisko()
                + "_" + DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm").format(LocalDateTime.now()));
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
        for (int x = LocalDateTime.now().getYear() - 30; x <= LocalDateTime.now().getYear(); x++) {
            yearChooseComboBox.addItem(x);
        }
        model.addColumn("Id");
        model.addColumn("Expenses");
        model.addColumn("Incomes");
        model.addColumn("Month Cov.");
        model.addColumn("Create Date");
        model.addColumn("Auth. Manager");
        model.addColumn("Club");
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
            for (Klub klub : klubList) {
                List<RozliczenieMiesieczne> rozliczenieMiesiecznetmp = session.createQuery("from rozliczenie_miesieczne where idKlubu.id=:id").setParameter("id", klub.getId()).list();
                if (rozliczenieMiesiecznetmp.size() > 0) {
                    rozliczenieMiesieczneList.addAll(rozliczenieMiesiecznetmp);
                    for (RozliczenieMiesieczne x : rozliczenieMiesiecznetmp) {
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
