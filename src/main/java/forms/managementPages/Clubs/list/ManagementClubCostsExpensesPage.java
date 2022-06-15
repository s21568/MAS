package forms.managementPages.Clubs.list;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.managementPages.Clubs.add.ManagementClubCostsAddExpenses;
import forms.managementPages.ManagementPage;
import models.Klub;
import models.Koszt;
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
import java.util.List;

public class ManagementClubCostsExpensesPage extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton managementButton;
    private JComboBox comboBox1;
    private JTable expensesTableList;
    private JButton saveButton;
    private JButton addNewExpense;
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private List<RozliczenieMiesieczne> rozliczenieMiesieczneList;
    private Manager authManager;
    private List<Klub> klubs;

    public ManagementClubCostsExpensesPage(Manager manager, List<RozliczenieMiesieczne> rozliczenieMiesiecznes, List<Klub> klubList) {
        rozliczenieMiesieczneList = rozliczenieMiesiecznes;
        authManager = manager;
        klubs=klubList;
        setInitialParametersAndActions();
    }

    private void setInitialParametersAndActions() {
        setTitle("Management Clubs Expenses Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        if (rozliczenieMiesieczneList.size() > 1) {
            addNewExpense.setVisible(false);
        }
        emailLabel.setText("Welcome " + authManager.getImie());
        expensesTableList.setModel(populateClientTableModel());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(authManager,klubs)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authManager)));
        addNewExpense.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddExpenses(authManager, rozliczenieMiesieczneList,klubs)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
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
        model.addColumn("Id");
        model.addColumn("Wartośc");
        model.addColumn("Nazwa");
        model.addColumn("Opis");
        for (RozliczenieMiesieczne rozliczenieMiesieczne : rozliczenieMiesieczneList) {
            List<Koszt> kosztList = rozliczenieMiesieczne.getListaKosztow();
            for (Koszt x : kosztList) {
                model.addRow(x.getFullInfo());
            }
        }
        return model;
    }
}
