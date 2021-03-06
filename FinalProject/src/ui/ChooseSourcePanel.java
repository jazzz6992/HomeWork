package ui;


import ui.interfaces.ChooseSourceListener;
import ui.interfaces.CurrentPanel;
import ui.interfaces.Ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseSourcePanel implements CurrentPanel {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton jsonButton;
    private JButton xmlButton;
    private JTextArea text;

    //в конструкторе устанавливаем слушателя для кнопок и задаем желаемое поведение при нажатии
    ChooseSourcePanel(ChooseSourceListener listener) {
        ChooseSourceListener listener1 = listener;
        text.setText("Выберите источник данных:");
        jsonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseSourceMade(Ui.Source.JSON);
            }
        });
        xmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseSourceMade(Ui.Source.XML);
            }
        });
    }

    @Override
    public JTextArea getContentArea() {
        return text;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
