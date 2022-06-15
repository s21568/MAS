package forms.managementPages.Clubs.list;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.managementPages.Clubs.add.ManagementClubCostsAddPage;
import forms.managementPages.ManagementPage;
import models.Klub;
import models.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagementClubsPage extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
    private JPanel LogInPanel;
    private JButton LogOut;
    private JLabel emailLabel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton managementButton;
    private JButton costsButton;
    private JButton classesButton1;
    private JTable clubsTableList;
    private JComboBox comboBox1;
    private JButton addCosts;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private List<Klub> klubList = new ArrayList<>();
    private List<Klub> selectedKlubList = new ArrayList<>();

    public ManagementClubsPage(Manager manager) {
        setTitle("Management Clubs Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        emailLabel.setText("Welcome " + manager.getImie());
        clubsTableList.setModel(populateClientTableModel());
        clubsTableList.setColumnSelectionAllowed(true);
        clubsTableList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage(manager)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(manager)));        addCosts.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddPage(manager)));
        addCosts.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddPage(manager)));
        costsButton.addActionListener(e -> {
            int[] selectedRows = clubsTableList.getSelectedRows();
            if (clubsTableList.getSelectedRowCount() > 0) {
                for (int x : selectedRows) {
                    selectedKlubList.add(this.klubList.get(x));
                }
                for (Klub x : selectedKlubList) {
                    System.out.println(Arrays.toString(x.getFullInfo()));
                }
                swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(manager, selectedKlubList));
            } else {
                swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(manager, klubList));
            }

        });
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
    }

    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Page");
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
            model.addColumn("DataOtwarcia");
            model.addColumn("GodzinaOtwarcia");
            model.addColumn("GodzinaZamkniecia");
            model.addColumn("Adres");

            klubList = session.createQuery("from klub").list();
            for (Klub x : klubList) {
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

