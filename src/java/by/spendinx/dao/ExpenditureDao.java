package by.spendinx.dao;

import by.spendinx.entity.Expenditure;

public interface ExpenditureDao extends Dao<Integer, Expenditure> {
    Expenditure findExpenditureByExpenditureItem(Integer id) throws DaoException;
}
