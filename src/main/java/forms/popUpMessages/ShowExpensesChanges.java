package forms.popUpMessages;

import models.Koszt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ShowExpensesChanges extends JFrame {
    private JTable expensesTableList;
    private JButton exitButton;
    private JPanel mainExpensesChangesPanel;
    private List<Koszt> kosztList;
    private final JFrame frame=this;

    public ShowExpensesChanges(List<Koszt> kosztList) {
        this.kosztList = kosztList;
        setContentPane(mainExpensesChangesPanel);
        exitButton.addActionListener(e -> this.dispose());

    }

    @Override
    public Container getContentPane() {
        setTitle("Expenses  Changes Table");
        setSize(500, 650);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        setVisible(true);
        setContentPane(mainExpensesChangesPanel);
        expensesTableList.setModel(populateIncomesChangesTable());
        return mainExpensesChangesPanel;
    }

    public DefaultTableModel populateIncomesChangesTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Wartośc");
        model.addColumn("Nazwa");
        model.addColumn("Opis");
        for (Koszt koszt : kosztList) {
            model.addRow(koszt.getFullInfo());
        }
        return model;
    }
}
