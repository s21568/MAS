package forms.popUpMessages;

import models.Przychod;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;

public class ShowIncomeChanges extends JFrame {
    private JTable incomesTableList;
    private JButton exitButton;
    private JPanel mainIncomesChangesPanel;
    private List<Przychod> przychodList;
    private final JFrame frame=this;

    public ShowIncomeChanges(List<Przychod> przychodList) {
        this.przychodList = przychodList;
        setContentPane(mainIncomesChangesPanel);
        exitButton.addActionListener(e -> this.dispose());
    }

    @Override
    public Container getContentPane() {
        setTitle("Incomes Changes Table");
        setSize(500, 650);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);
        setContentPane(mainIncomesChangesPanel);
        incomesTableList.setModel(populateIncomesChangesTable());
        return mainIncomesChangesPanel;
    }

    public DefaultTableModel populateIncomesChangesTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Wartośc");
        model.addColumn("Nazwa");
        model.addColumn("Opis");
        for (Przychod przychod : przychodList) {
            model.addRow(przychod.getFullInfo());
        }
        return model;
    }
}
