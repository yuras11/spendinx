package by.spendinx.dao;

import by.spendinx.entity.Income;

public interface IncomeDao extends Dao<Integer, Income> {
    Income findIncomeByIncomeSource(Integer id) throws DaoException;
}
