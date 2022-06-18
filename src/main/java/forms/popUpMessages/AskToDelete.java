package forms.popUpMessages;

import models.*;

import javax.swing.*;
import java.awt.*;

public class AskToDelete extends JFrame {
    private JLabel continueLabel;
    private JButton YES;
    private JButton NO;
    private JPanel mainPanel;

    public AskToDelete() {
        setInitialParametersAndActions();
        NO.addActionListener(e -> this.dispose());
    }

    public void deleteKoszt(RozliczenieMiesieczne rozliczenieMiesieczne, Koszt koszt) {
        YES.addActionListener(x -> {
            rozliczenieMiesieczne.removeKoszt(koszt);
            this.dispose();
        });
    }

    public void deletePrzychod(RozliczenieMiesieczne rozliczenieMiesieczne, Przychod przychod) {
        YES.addActionListener(x -> {
            rozliczenieMiesieczne.removePrzychod(przychod);
            this.dispose();
        });
    }

    private void setInitialParametersAndActions() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(getContentPane());

    }

    @Override
    public Container getContentPane() {
        setTitle("Delete confirm");
        setSize(450, 200);
        setVisible(true);
        setContentPane(mainPanel);
        return mainPanel;
    }

}
