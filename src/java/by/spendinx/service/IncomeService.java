package by.spendinx.service;

import by.spendinx.entity.Income;

public interface IncomeService {
    Income findEntityById(Integer id) throws ServiceException;
}
