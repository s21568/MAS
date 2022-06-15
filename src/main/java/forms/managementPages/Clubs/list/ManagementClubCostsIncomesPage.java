package forms.managementPages.Clubs.list;

import forms.MainPage;
import forms.SwingUiChanger;
import forms.managementPages.Clubs.add.ManagementClubCostsAddIncomes;
import forms.managementPages.ManagementPage;
import models.Manager;
import models.Przychod;
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

public class ManagementClubCostsIncomesPage extends JFrame {
    private JPanel managementPageMainPanel;
    private JPanel topPanel;
    private JScrollPane toolBoxScroll;
    private JButton mainButton;
    private JButton managementButton;
    private JButton saveButton;
    private JComboBox comboBox1;
    private JTable incomesTableList;
    private JButton printButton;
    private JButton addNewIncome;
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private List<RozliczenieMiesieczne> rozliczenieMiesieczneList;
    private Manager authManager;

    public ManagementClubCostsIncomesPage(Manager manager,List<RozliczenieMiesieczne> rozliczenieMiesieczne) {
        setInitialParametersAndActions();
        rozliczenieMiesieczneList = rozliczenieMiesieczne;
        authManager=manager;
    }

    private void setInitialParametersAndActions() {
        setTitle("Management Clubs Incomes Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        emailLabel.setText("Welcome "+authManager.getImie());
        incomesTableList.setModel(populateClientTableModel());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage(authManager)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authManager)));
        addNewIncome.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddIncomes(authManager)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
    }

    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Incomes Page");
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
            List<Przychod> przychodList = rozliczenieMiesieczne.getListaPrzychodow();
            for (Przychod x : przychodList) {
                model.addRow(x.getFullInfo());
            }
        }

        return model;
    }
}
