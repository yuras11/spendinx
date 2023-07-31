package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.ExpenditureDaoImpl;
import by.spendinx.entity.Expenditure;

import java.sql.Connection;

public class ExpenditureServiceImpl extends ServiceImpl implements ExpenditureService {
    private final Connection connection;
    public ExpenditureServiceImpl() throws DaoException {
        this.connection = connectionPool.getConnection();
    }
    @Override
    public Expenditure findEntityById(Integer id) throws ServiceException {
        try {
            return new ExpenditureDaoImpl(connection).findEntityById(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Expenditure findExpenditureByExpenditureItem(Integer id) throws ServiceException {
        try {
            return new ExpenditureDaoImpl(connection).findExpenditureByExpenditureItem(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
