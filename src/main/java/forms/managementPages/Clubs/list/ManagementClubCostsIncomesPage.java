package forms.managementPages.Clubs.list;

import forms.*;
import forms.managementPages.Clubs.add.ManagementClubCostsAddIncomes;
import forms.managementPages.ManagementPage;
import forms.popUpMessages.AskToContinue;
import forms.popUpMessages.AskToDelete;
import forms.popUpMessages.AskToPrint;
import forms.popUpMessages.AskToSaveIncomes;
import forms.unilities.SwingUiChanger;
import models.Klub;
import models.Manager;
import models.Przychod;
import models.RozliczenieMiesieczne;

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
    private JButton deleteButton;
    private final SwingUiChanger swingUiChanger = new SwingUiChanger();
    private final List<RozliczenieMiesieczne> rozliczenieMiesieczneList;

    private final List<Przychod> changedPrzychod = new ArrayList<>();
    private final Manager authManager;
    private final List<Klub> klubs;
    private final JFrame jFrame;
    private final List<Integer> wrongRowsToBeHighlited = new ArrayList<>();

    public ManagementClubCostsIncomesPage(Manager manager, List<RozliczenieMiesieczne> rozliczenieMiesieczne, List<Klub> klubList) {
        authManager = manager;
        rozliczenieMiesieczneList = rozliczenieMiesieczne;
        klubs = klubList;
        setInitialParametersAndActions();
        jFrame = this;
    }

    private void setInitialParametersAndActions() {
        setContentPane(managementPageMainPanel);
        if (rozliczenieMiesieczneList.size() > 1) {
            addNewIncome.setVisible(false);
        }
        emailLabel.setText("Welcome " + authManager.getImie());
        incomesTableList.setModel(populateClientTableModel());
        mainButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsPage(authManager, klubs)));
        managementButton.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementPage(authManager)));
        addNewIncome.addActionListener(e -> swingUiChanger.changeSwingUi(this, new ManagementClubCostsAddIncomes(authManager, rozliczenieMiesieczneList, klubs)));
        saveButton.addActionListener(e -> saveProgressToDb());
        printButton.addActionListener(e -> {
            if (chceckTableCellsValid())
                askToPrint();
        });
        LogOut.addActionListener(e -> swingUiChanger.changeSwingUi(this, new MainPage()));
        deleteButton.addActionListener(e -> {
            AskToDelete askToDelete= new AskToDelete();
            Przychod przychodToDelete=rozliczenieMiesieczneList.get(0).getListaPrzychodow().get(incomesTableList.getSelectedRow());
            askToDelete.deletePrzychod(rozliczenieMiesieczneList.get(0),przychodToDelete);
            jFrame.repaint();
        });
    }

    private void saveProgressToDb() {

        if (chceckTableCellsValid()) {
            wrongRowsToBeHighlited.clear();
            this.repaint();
            if (!changedPrzychod.isEmpty())
                askToSaveIncomes();
        }
    }

    private boolean chceckTableCellsValid() {
        int wrongInputDataCell = 0;
        wrongRowsToBeHighlited.clear();
        List<Przychod> przychodList = rozliczenieMiesieczneList.get(0).getListaPrzychodow();
        for (int i = 0; i < incomesTableList.getRowCount(); i++) {
            wrongInputDataCell = 0;
            if (Long.parseLong(incomesTableList.getValueAt(i, 0).toString()) == przychodList.get(i).getId()) {
                try {
                    if (Double.parseDouble(incomesTableList.getValueAt(i, 1).toString()) == przychodList.get(i).getWartosc()) {
                        if (incomesTableList.getValueAt(i, 2).toString().equals(przychodList.get(i).getNazwa())) {
                            if (!incomesTableList.getValueAt(i, 3).toString().equals(przychodList.get(i).getOpis())) {
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

                } catch (NumberFormatException e) {
                    wrongInputDataCell++;
                    wrongRowsToBeHighlited.add(i);
                    this.repaint();
                }
            }
        }
        if (wrongInputDataCell == 0) {
            wrongRowsToBeHighlited.clear();
            this.repaint();
            return true;
        } else {
            this.repaint();
            return false;
        }
    }


    private void askToSaveIncomes() {
        AskToSaveIncomes askToSaveIncomes = new AskToSaveIncomes(changedPrzychod, rozliczenieMiesieczneList);
        askToSaveIncomes.addWindowListener(new WindowAdapter() {
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
        askToContinue.setPrzychodList(changedPrzychod);
        askToContinue.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
    }

    private AskToPrint askToPrint() {
        return new AskToPrint(incomesTableList, "INCOMES_" + rozliczenieMiesieczneList.get(0).getMiesiacPokrycia()
                + "-" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getImie()
                + "_" + rozliczenieMiesieczneList.get(0).getIdManageraAutoryzujacego().getNazwisko()
                + "_" + DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm").format(LocalDateTime.now()));
    }

    @Override
    public Container getContentPane() {
        setTitle("Management Clubs Incomes Page");
        setSize(650, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(managementPageMainPanel);
        incomesTableList.getColumnModel().getColumn(1).setCellRenderer(new WrongCellValueRenderer());
        return managementPageMainPanel;
    }

    private DefaultTableModel populateClientTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Wartośc");
        model.addColumn("Nazwa");
        model.addColumn("Opis");
        for (Przychod x : rozliczenieMiesieczneList.get(0).getListaPrzychodow()) {
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
            if (wrongRowsToBeHighlited.contains(row)) {
                c.setBackground(Color.RED);
            } else {
                c.setBackground(Color.WHITE);
            }
            return c;
        }
    }
}
