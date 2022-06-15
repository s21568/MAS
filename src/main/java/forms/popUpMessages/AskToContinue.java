package forms.popUpMessages;

import forms.unilities.SwingUiChanger;
import forms.managementPages.ManagementPage;
import models.Manager;

import javax.swing.*;
import java.awt.*;

public class AskToContinue extends JFrame {
    private JButton NO;
    private JButton YES;
    private JLabel continueLabel;
    private JPanel mainContinuePanel;
    private SwingUiChanger swingUiChanger = new SwingUiChanger();
    private JFrame frame;
    private Manager authManager;

    public AskToContinue(JFrame jFrame, Manager manager) {
        this.setContentPane(getContentPane());
        frame = jFrame;
        authManager = manager;
        setInitialParametersAndActions();

    }

    private void setInitialParametersAndActions() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(getContentPane());
        NO.addActionListener(e -> {

            swingUiChanger.changeSwingUi(frame, new ManagementPage(authManager));
            this.dispose();
        });
        YES.addActionListener(x -> {
            this.dispose();
        });
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
