package by.spendinx.dao;

import by.spendinx.entity.Currency;

public interface CurrencyDao extends Dao<Integer, Currency> {
    Currency findCurrencyByName(String name);
    boolean updateCourse(Currency currency, float course);
}
