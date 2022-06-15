package forms.popUpMessages;


import forms.unilities.PrintingUtilities;

import javax.swing.*;
import java.awt.*;

public class AskToPrint extends JFrame {
    private JButton NO;
    private JButton YES;
    private JLabel printLabel;
    private JPanel mainPrintPanel;
    private final JTable table;
    private final String name;

    public AskToPrint(JTable jTable, String name) {
        this.setContentPane(getContentPane());
        table = jTable;
        this.name = name;
        setInitialParametersAndActions();

    }

    private void setInitialParametersAndActions() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(getContentPane());
        NO.addActionListener(e -> this.dispose());
        YES.addActionListener(x -> {
            PrintingUtilities printingUtilities = new PrintingUtilities();
            printingUtilities.print(table, name);
            this.dispose();
        });
    }

    @Override
    public Container getContentPane() {
        setTitle("Print Document");
        setSize(450, 200);
        setVisible(true);
        setContentPane(mainPrintPanel);
        return mainPrintPanel;
    }
}
