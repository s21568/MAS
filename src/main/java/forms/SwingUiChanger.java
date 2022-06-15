package forms;

import forms.clientPages.ClientList;

import javax.swing.*;

public class SwingUiChanger {
    public void changeSwingUi(JFrame jFrame,JFrame switchTo){
        switchTo.setVisible(true);
        switchTo.setContentPane(switchTo.getContentPane());
        jFrame.dispose();
    }
}
