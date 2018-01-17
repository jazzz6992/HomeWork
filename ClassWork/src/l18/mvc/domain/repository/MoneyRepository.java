package l18.mvc.domain.repository;

import l18.mvc.domain.entity.Money;

public interface MoneyRepository {
    Money get();

    void save(Money money);

}
