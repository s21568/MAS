package forms.managementPages.Clubs.list;

import forms.*;
import forms.managementPages.Clubs.add.ManagementClubCostsAddIncomes;
import forms.managementPages.ManagementPage;
import forms.popUpMessages.AskToContinue;
import forms.popUpMessages.AskToPrint;
import forms.popUpMessages.AskToSaveIncomes;
import forms.unilities.SwingUiChanger;
import models.Klub;
import models.Manager;
import models.Przychod;
import models.RozliczenieMiesieczne;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private JButton addNewIncome;
    private JPanel LogInPanel;
    private JLabel emailLabel;
    private JButton LogOut;
    private JPanel mainPanel;
    private JButton printButton;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private List<RozliczenieMiesieczne> rozliczenieMiesieczneList;
    private Manager authManager;
    private List<Klub> klubs;
    private JFrame jFrame;

    public ManagementClubCostsIncomesPage(Manager manager, List<RozliczenieMiesieczne> rozliczenieMiesieczne, List<Klub> klubList) {
        authManager = manager;
        rozliczenieMiesieczneList = rozliczenieMiesieczne;
        klubs = klubList;
        setInitialParametersAndActions();
        jFrame = this;
    }

    private void setInitialParametersAndActions() {
        setTitle("Management Clubs Incomes Page");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(managementPageMainPanel);
        if (rozliczenieMiesieczneList.size() > 1) {
            addNewIncome.setVisible(false);
        }
        emailLabel.setText("Welcome " + authManager.getImie());
        incomesTableList.setModel(populateClientTableModel());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(authManager, klubs)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authManager)));
        addNewIncome.addActionListener(e -> {
            swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddIncomes(authManager, rozliczenieMiesieczneList, klubs));
        });
        saveButton.addActionListener(e -> saveProgresToDb());
        printButton.addActionListener(e -> print());
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
    }

    private void saveProgresToDb() {
        List<Przychod> changedPrzychod = new ArrayList<>();
        List<Przychod> przychodList = rozliczenieMiesieczneList.get(0).getListaPrzychodow();
        for (int i = 0; i < incomesTableList.getRowCount(); i++) {
            if (Long.parseLong(incomesTableList.getValueAt(i, 0).toString()) == przychodList.get(i).getId()) {
                if (Double.parseDouble(incomesTableList.getValueAt(i, 1).toString()) == przychodList.get(i).getWartosc()) {
                    if (incomesTableList.getValueAt(i, 2).toString().equals(przychodList.get(i).getNazwa())) {
                        if (incomesTableList.getValueAt(i, 3).toString().equals(przychodList.get(i).getOpis())) {
                        } else {
                            if (!changedPrzychod.contains(przychodList.get(i))) {
                                przychodList.get(i).setOpis(incomesTableList.getValueAt(i, 3).toString());
                                changedPrzychod.add(przychodList.get(i));
                            } else {
                                przychodList.get(i).setOpis(incomesTableList.getValueAt(i, 3).toString());
                            }
                        }
                    } else {
                        if (!changedPrzychod.contains(przychodList.get(i))) {
                            przychodList.get(i).setNazwa(incomesTableList.getValueAt(i, 2).toString());
                            changedPrzychod.add(przychodList.get(i));
                        } else {
                            przychodList.get(i).setNazwa(incomesTableList.getValueAt(i, 2).toString());
                        }
                    }
                } else {
                    if (!changedPrzychod.contains(przychodList.get(i))) {
                        przychodList.get(i).setWartosc(Double.parseDouble(incomesTableList.getValueAt(i, 1).toString()));
                        changedPrzychod.add(przychodList.get(i));
                    }
                }
            }
        }
        AskToSaveIncomes askToSaveIncomes = new AskToSaveIncomes(changedPrzychod, rozliczenieMiesieczneList);
        askToSaveIncomes.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                e.getWindow().dispose();
                AskToPrint askToPrint = new AskToPrint(incomesTableList, "INCOMES_" + rozliczenieMiesieczneList.get(0).getMiesiacPokrycia()
                        + "-" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getImie()
                        + "_" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getNazwisko()
                        + "_" + DateTimeFormatter.ofPattern("dd_MM_yyyy").format(LocalDateTime.now()));
                askToPrint.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        e.getWindow().dispose();
                        AskToContinue askToContinue = new AskToContinue(jFrame, authManager);
                        askToContinue.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                e.getWindow().dispose();
                            }
                        });
                    }
                });
            }
        });
    }

    private void print() {
        AskToPrint askToPrint = new AskToPrint(incomesTableList, "INCOMES_" +rozliczenieMiesieczneList.get(0).getMiesiacPokrycia()
                + "-" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getImie()
                + "_" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getNazwisko()
                + "_" + DateTimeFormatter.ofPattern("dd_MM_yyyy").format(LocalDateTime.now()));
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
