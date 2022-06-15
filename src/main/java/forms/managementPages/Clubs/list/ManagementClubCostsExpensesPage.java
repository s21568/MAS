package forms.managementPages.Clubs.list;

import forms.*;
import forms.managementPages.Clubs.add.ManagementClubCostsAddExpenses;
import forms.managementPages.ManagementPage;
import forms.popUpMessages.AskToContinue;
import forms.popUpMessages.AskToPrint;
import forms.popUpMessages.AskToSaveExpenses;
import forms.popUpMessages.AskToSaveIncomes;
import forms.unilities.SwingUiChanger;
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private JButton printButton;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private List<RozliczenieMiesieczne> rozliczenieMiesieczneList;
    private Manager authManager;
    private List<Klub> klubs;
    private JFrame jFrame;

    public ManagementClubCostsExpensesPage(Manager manager, List<RozliczenieMiesieczne> rozliczenieMiesiecznes, List<Klub> klubList) {
        rozliczenieMiesieczneList = rozliczenieMiesiecznes;
        authManager = manager;
        klubs = klubList;
        setInitialParametersAndActions();
        jFrame=this;
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
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(authManager, klubs)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authManager)));
        addNewExpense.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddExpenses(authManager, rozliczenieMiesieczneList, klubs)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        saveButton.addActionListener(e -> saveProgresToDb());
        printButton.addActionListener(e -> print());
    }

    private void saveProgresToDb() {
        List<Koszt> changedKoszt = new ArrayList<>();
        List<Koszt> kosztList = rozliczenieMiesieczneList.get(0).getListaKosztow();
        for (int i = 0; i < expensesTableList.getRowCount(); i++) {
            if (Long.parseLong(expensesTableList.getValueAt(i, 0).toString()) == kosztList.get(i).getId()) {
                if (Double.parseDouble(expensesTableList.getValueAt(i, 1).toString()) == kosztList.get(i).getWartosc()) {
                    if (expensesTableList.getValueAt(i, 2).toString().equals(kosztList.get(i).getNazwa())) {
                        if (expensesTableList.getValueAt(i, 3).toString().equals(kosztList.get(i).getOpis())) {
                        } else {
                            if (!changedKoszt.contains(kosztList.get(i))) {
                                kosztList.get(i).setOpis(expensesTableList.getValueAt(i, 3).toString());
                                changedKoszt.add(kosztList.get(i));
                            } else {
                                kosztList.get(i).setOpis(expensesTableList.getValueAt(i, 3).toString());
                            }
                        }
                    } else {
                        if (!changedKoszt.contains(kosztList.get(i))) {
                            kosztList.get(i).setNazwa(expensesTableList.getValueAt(i, 2).toString());
                            changedKoszt.add(kosztList.get(i));
                        } else {
                            kosztList.get(i).setNazwa(expensesTableList.getValueAt(i, 2).toString());
                        }
                    }
                } else {
                    if (!changedKoszt.contains(kosztList.get(i))) {
                        kosztList.get(i).setWartosc(Double.parseDouble(expensesTableList.getValueAt(i, 1).toString()));
                        changedKoszt.add(kosztList.get(i));
                    }
                }
            }
        }
        AskToSaveExpenses askToSaveExpenses = new AskToSaveExpenses(changedKoszt, rozliczenieMiesieczneList);
        askToSaveExpenses.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                e.getWindow().dispose();
                AskToPrint askToPrint = new AskToPrint(expensesTableList, "EXPENSES_"+rozliczenieMiesieczneList.get(0).getMiesiacPokrycia()
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
        AskToPrint askToPrint = new AskToPrint(expensesTableList, "EXPENSES_"+rozliczenieMiesieczneList.get(0).getMiesiacPokrycia()
                + "-" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getImie()
                + "_" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getNazwisko()
                + "_" + DateTimeFormatter.ofPattern("dd_MM_yyyy").format(LocalDateTime.now()));
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
