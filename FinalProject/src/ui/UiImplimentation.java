package ui;

import entity.Stock;
import entity.StockExchange;
import manager.Manager;
import manager.buttonChoices.Action;
import manager.buttonChoices.Source;

import javax.swing.*;
import java.util.List;

public class UiImplimentation extends JFrame implements Ui, ChooseSourceListener, ChooseActionListener {
    private Manager manager;
    private CurrentPanel currentPanel;

    public static void main(String[] args) {
        UiImplimentation ui = new UiImplimentation();
        ui.setManager(new Manager(ui));
        ui.setCurrentPanel(new ChooseSourcePanel(ui));
        ui.setContentPane(ui.getCurrentPanel().getMainPanel());
        ui.setSize(300, 100);
        ui.setVisible(true);
        ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public CurrentPanel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(CurrentPanel currentPanel) {
        this.currentPanel = currentPanel;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    public void print(String stocks) {
        currentPanel.getContentArea().setText(stocks);
    }

    @Override
    public void print(StockExchange stockExchange) {
        currentPanel.getContentArea().setText(stockExchange.toString());
    }

    @Override
    public void print(List<Stock> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Stock s :
                list) {
            stringBuilder.append(s.toString());
        }
        currentPanel.getContentArea().setText(stringBuilder.toString());
    }

    @Override
    public void onChooseSourceMade(Source source) {
        currentPanel = new ContentPanel(this);
        setContentPane(currentPanel.getMainPanel());
        setSize(800, 600);
        manager.getFile(source);
    }

    @Override
    public void onChooseActionMade(Action action) {
        if (action == Action.SEARCH) {
            JTextField searchField = ((ContentPanel) currentPanel).getSearchField();
            String key = searchField.getText();
            searchField.setText("");
            manager.search(key);
        } else if (action.name().toLowerCase().contains("sort")) {
            manager.sort(action);
        } else if (action == Action.SHOW_ALL) {
            manager.showAll();
        } else if (action == Action.BACK) {
            manager.resetAll();
            currentPanel = new ChooseSourcePanel(this);
            setContentPane(currentPanel.getMainPanel());
            pack();
        }
    }
}