package ui;

import manager.Manager;
import ui.buttonChoices.Action;
import ui.buttonChoices.Source;

import javax.swing.*;

public class UiImplimentation extends JFrame implements Ui, ChooseSourceListener, ChooseActionListener {
    private Manager manager;
    //панель, испоьзуемая в JFrame в данный момент
    private CurrentPanel currentPanel;

    //инициализация пользовательского интерфейса и добавление в менеджер ссылки на него
    public void initializeUI() {
        manager = Manager.getInstance();
        manager.setUi(this);
        setCurrentPanel(new ChooseSourcePanel(this));
        setContentPane(getCurrentPanel().getMainPanel());
        setSize(300, 100);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private CurrentPanel getCurrentPanel() {
        return currentPanel;
    }

    private void setCurrentPanel(CurrentPanel currentPanel) {
        this.currentPanel = currentPanel;
    }

    @Override
    public void print(String content) {
        currentPanel.getContentArea().setText(content);
    }

    //устанавливает в JFrame панель содержимого и запускает загрузку данных с последующим парсингом
    @Override
    public void onChooseSourceMade(Source source) {
        currentPanel = new ContentPanel(this);
        setContentPane(currentPanel.getMainPanel());
        setSize(800, 600);
        manager.getData(source);
    }

    /*
      в зависимости от выбора пользователя запускает обработку
      данных или меняет панель на панель выбора источника данных (в этом случае обнуляет
      поля менеджера)
     */
    @Override
    public void onChooseActionMade(Action action) {
        if (action == Action.SEARCH) {
            JTextField searchField = ((ContentPanel) currentPanel).getSearchField();
            String key = searchField.getText();
            if (!key.equals(ContentPanel.SEARCH_HINT)) {
                manager.search(key);
            }
        } else if (action.name().toLowerCase().contains("sort")) {
            manager.sort(action);
        } else if (action == Action.SHOW_ALL) {
            manager.showAll();
        } else if (action == Action.BACK) {
            manager.resetAll();
            currentPanel = new ChooseSourcePanel(this);
            setContentPane(currentPanel.getMainPanel());
            pack();
        } else if (action == Action.AVERAGE) {
            manager.average();
        }
    }
}