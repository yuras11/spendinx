package by.spendinx.service;

import by.spendinx.entity.ExpenditureItem;

public interface ExpenditureItemService {
    ExpenditureItem findEntityById(Integer id) throws ServiceException;
    ExpenditureItem findExpenditureItemByName(String name) throws ServiceException;
}
