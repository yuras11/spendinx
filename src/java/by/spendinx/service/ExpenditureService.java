package by.spendinx.service;

import by.spendinx.entity.Expenditure;

public interface ExpenditureService {
    Expenditure findEntityById(Integer id) throws ServiceException;
}
