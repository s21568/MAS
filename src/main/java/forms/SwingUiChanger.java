package forms;

import forms.clientPages.ClientList;

import javax.swing.*;

public class SwingUiChanger {
    public void changeSwingUi(JFrame jFrame,JFrame switchTo){
        switchTo.setSize(1, 1);
        jFrame.setContentPane(switchTo.getContentPane());
        switchTo.setVisible(true);
        switchTo.setContentPane(switchTo.getContentPane());
        jFrame.dispose();
    }
}
