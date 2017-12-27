package ui;

import manager.buttonChoices.Source;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseSourcePanel implements CurrentPanel {
    private ChooseSourceListener listener;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton jsonButton;
    private JButton xmlButton;
    private JTextArea text;

    public ChooseSourcePanel(ChooseSourceListener listener) {
        this.listener = listener;
        text.setText("Выберите источник данных:");
        jsonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseSourceMade(Source.JSON);
            }
        });
        xmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseSourceMade(Source.XML);
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
