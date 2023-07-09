package by.spendinx.service;

import by.spendinx.entity.Currency;
import by.spendinx.entity.User;

public interface CurrencyService {
    Currency findCurrencyByName(String name) throws ServiceException;
    Currency findEntityById(Integer id) throws ServiceException;
    boolean updateCourse(Currency currency, float course);
}
