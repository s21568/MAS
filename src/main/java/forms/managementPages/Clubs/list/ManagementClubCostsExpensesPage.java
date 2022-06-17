package forms.managementPages.Clubs.list;

import forms.*;
import forms.managementPages.Clubs.add.ManagementClubCostsAddExpenses;
import forms.managementPages.ManagementPage;
import forms.popUpMessages.AskToContinue;
import forms.popUpMessages.AskToPrint;
import forms.popUpMessages.AskToSaveExpenses;
import forms.unilities.SwingUiChanger;
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
    private final List<RozliczenieMiesieczne> rozliczenieMiesieczneList;
    private final List<Koszt> changedKoszt = new ArrayList<>();
    private final Manager authManager;
    private final List<Klub> klubs;
    private final JFrame jFrame;
    private final List<Integer> wrongRows = new ArrayList<>();

    public ManagementClubCostsExpensesPage(Manager manager, List<RozliczenieMiesieczne> rozliczenieMiesiecznes, List<Klub> klubList) {
        rozliczenieMiesieczneList = rozliczenieMiesiecznes;
        authManager = manager;
        klubs = klubList;
        setInitialParametersAndActions();
        jFrame = this;
    }

    private void setInitialParametersAndActions() {
        setContentPane(managementPageMainPanel);
        if (rozliczenieMiesieczneList.size() > 1) {
            addNewExpense.setVisible(false);
        }
        emailLabel.setText("Welcome " + authManager.getImie());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(authManager, klubs)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authManager)));
        addNewExpense.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddExpenses(authManager, rozliczenieMiesieczneList, klubs)));
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        saveButton.addActionListener(e -> saveProgressToDb());
        printButton.addActionListener(e -> askToPrint());
        expensesTableList.setModel(populateClientTableModel());
    }

    private void saveProgressToDb() {

        int wrong = 0;
        wrongRows.clear();
        List<Koszt> kosztList = rozliczenieMiesieczneList.get(0).getListaKosztow();
        for (int i = 0; i < expensesTableList.getRowCount(); i++) {
            if (Long.parseLong(expensesTableList.getValueAt(i, 0).toString()) == kosztList.get(i).getId()) {
                try {
                    if (Double.parseDouble(expensesTableList.getValueAt(i, 1).toString()) == kosztList.get(i).getWartosc()) {
                        if (expensesTableList.getValueAt(i, 2).toString().equals(kosztList.get(i).getNazwa())) {
                            if (!expensesTableList.getValueAt(i, 3).toString().equals(kosztList.get(i).getOpis())) {
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

                } catch (NumberFormatException e) {
                    wrong++;
                    wrongRows.add(i);
                    this.repaint();
                }
            }
        }

        if (wrong == 0) {
            wrongRows.clear();
            this.repaint();
            if (!changedKoszt.isEmpty())
                askToSaveExpenses();
        }
    }

    private void askToSaveExpenses() {
        AskToSaveExpenses askToSaveExpenses = new AskToSaveExpenses(changedKoszt, rozliczenieMiesieczneList);
        askToSaveExpenses.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                e.getWindow().dispose();
                askToPrint().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        e.getWindow().dispose();
                        askToContinue();
                    }
                });
            }
        });
    }

    private void askToContinue() {
        AskToContinue askToContinue = new AskToContinue(jFrame, authManager);
        askToContinue.setKosztList(changedKoszt);
        askToContinue.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
    }

    private AskToPrint askToPrint() {
        return new AskToPrint(expensesTableList, "EXPENSES_" + rozliczenieMiesieczneList.get(0).getMiesiacPokrycia()
                + "-" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getImie()
                + "_" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getNazwisko()
                + "_" + DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm").format(LocalDateTime.now()));
    }

    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Expenses Page");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(managementPageMainPanel);
        expensesTableList.getColumnModel().getColumn(1).setCellRenderer(new WrongCellValueRenderer());
        return managementPageMainPanel;
    }

    public DefaultTableModel populateClientTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Wartośc");
        model.addColumn("Nazwa");
        model.addColumn("Opis");
            for (Koszt x : rozliczenieMiesieczneList.get(0).getListaKosztow()) {
                model.addRow(x.getFullInfo());
            }

        return model;
    }

    private class WrongCellValueRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int col) {

            Component c = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, col);
            if (wrongRows.contains(row)) {
                c.setBackground(Color.RED);
            }
            if (col == 0) {
                c.setEnabled(false);
            }
            return c;
        }
    }
}
