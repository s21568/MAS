package forms.popUpMessages;

import forms.managementPages.Clubs.list.ManagementClubCostsIncomesPage;
import forms.unilities.SwingUiChanger;
import forms.managementPages.ManagementPage;
import models.Koszt;
import models.Manager;
import models.Przychod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class AskToContinue extends JFrame {
    private JButton NO;
    private JButton YES;
    private JLabel continueLabel;
    private JPanel mainContinuePanel;
    private SwingUiChanger swingUiChanger = new SwingUiChanger();
    private List<Przychod> przychodList;
    private List<Koszt> kosztList;
    private JFrame frame;
    private Manager authManager;

    public AskToContinue(JFrame jFrame, Manager manager) {
        this.setContentPane(getContentPane());
        frame = jFrame;
        authManager = manager;
        setInitialParametersAndActions();
    }

    public void setKosztList(List<Koszt> kosztList) {
        NO.addActionListener(e -> {
            if (!kosztList.isEmpty()) {
                ShowExpensesChanges showExpensesChanges = new ShowExpensesChanges(kosztList);
                showExpensesChanges.setVisible(true);
                showExpensesChanges.setContentPane(showExpensesChanges.getContentPane());
            }
            swingUiChanger.changeSwingUi(frame, new ManagementPage(authManager));
            this.dispose();
        });

    }

    public void setPrzychodList(List<Przychod> przychodList) {
        this.przychodList = przychodList;
        NO.addActionListener(e -> {
            if (!przychodList.isEmpty()) {
                ShowIncomeChanges showIncomeChanges = new ShowIncomeChanges(przychodList);
                showIncomeChanges.setVisible(true);
                showIncomeChanges.setContentPane(showIncomeChanges.getContentPane());
            }
            swingUiChanger.changeSwingUi(frame, new ManagementPage(authManager));
            this.dispose();
        });
    }

    private void setInitialParametersAndActions() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(getContentPane());
        YES.addActionListener(x -> this.dispose());
    }

    @Override
    public Container getContentPane() {
        setTitle("Print Document");
        setSize(450, 200);
        setVisible(true);
        setContentPane(mainContinuePanel);
        return mainContinuePanel;
    }
}
