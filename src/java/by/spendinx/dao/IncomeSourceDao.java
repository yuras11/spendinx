package by.spendinx.dao;

import by.spendinx.entity.IncomeSource;

public interface IncomeSourceDao extends Dao<Integer, IncomeSource> {
    IncomeSource findIncomeSourceByName(String name) throws DaoException;
}
