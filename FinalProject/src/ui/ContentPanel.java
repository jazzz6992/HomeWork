package ui;

import ui.interfaces.ChooseActionListener;
import ui.interfaces.CurrentPanel;
import ui.interfaces.Ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ContentPanel implements CurrentPanel {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton sortByPriceButton;
    private JButton searchButton;
    private JPanel textPanel;
    private JTextField searchField;
    private JTextArea contentArea;
    private JButton showAllButton;
    private JButton sortByNameButton;
    private JButton backToSource;
    private JScrollPane scrollPane;
    private JButton showAveragePriceButton;
    public final static String SEARCH_HINT = "Search...";

    //в конструкторе устанавливаем слушателя для кнопок и задаем желаемое поведение при нажатии
    ContentPanel(ChooseActionListener listener) {
        searchField.setToolTipText(SEARCH_HINT);
        searchField.setText(SEARCH_HINT);
        sortByPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseActionMade(Ui.Action.SORT_BY_PRICE);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseActionMade(Ui.Action.SEARCH);
                searchField.setText(SEARCH_HINT);
            }
        });
        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseActionMade(Ui.Action.SHOW_ALL);
            }
        });
        showAveragePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseActionMade(Ui.Action.AVERAGE);
            }
        });
        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseActionMade(Ui.Action.SORT_BY_NAME);
            }
        });
        backToSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onChooseActionMade(Ui.Action.BACK);
            }
        });
        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(SEARCH_HINT)) {
                    searchField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().equals("")) {
                    searchField.setText(SEARCH_HINT);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTextArea getContentArea() {
        return contentArea;
    }
}
