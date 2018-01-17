package l18.mvc.domain.usecase;

import l18.mvc.domain.entity.GetMoneyResult;
import l18.mvc.domain.entity.Money;
import l18.mvc.domain.repository.MoneyRepository;

public class GetMoneyUseCase implements UseCase {
    private MoneyRepository moneyRepository;

    public GetMoneyUseCase(MoneyRepository moneyRepository) {
        this.moneyRepository = moneyRepository;
    }

    public void execute(Money money, GetMoneyListener getMoneyListener) {
        Money currentMoney = moneyRepository.get();
        if (currentMoney.getMoney() >= money.getMoney()) {
            Money newMoney = new Money();
            newMoney.setMoney(currentMoney.getMoney() - money.getMoney());
            moneyRepository.save(newMoney);
            getMoneyListener.onResult(new GetMoneyResult());
        } else {
            getMoneyListener.onError();
        }
    }

    public interface GetMoneyListener {
        void onResult(GetMoneyResult getMoneyResult);

        void onError();
    }
}
