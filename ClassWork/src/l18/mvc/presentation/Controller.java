package l18.mvc.presentation;

import l18.mvc.domain.entity.GetMoneyResult;
import l18.mvc.domain.entity.Money;
import l18.mvc.domain.usecase.GetMoneyUseCase;

import java.util.List;

public class Controller {

    private List<Model> modelList;

    private View view;

    public Controller(View view) {
        this.view = view;
    }

    public void getMoney() {
        GetMoneyUseCase getMoneyUseCase = new GetMoneyUseCase();
        Money money = new Money();
        money.setMoney(50);
        getMoneyUseCase.execute(money, new GetMoneyUseCase.GetMoneyListener() {
            @Override
            public void onResult(GetMoneyResult getMoneyResult) {
                System.out.println(getMoneyResult.isOk());
            }

            @Override
            public void onError() {
                System.out.println("false");
            }
        });
    }
}
