package by.spendinx.dao;

import by.spendinx.entity.ExpenditureItem;

public interface ExpenditureItemDao extends Dao<Integer, ExpenditureItem> {
    ExpenditureItem findExpenditureItemByName(String name) throws DaoException;
}
