package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.IncomeDaoImpl;
import by.spendinx.entity.Income;

import java.sql.Connection;

public class IncomeServiceImpl extends ServiceImpl implements IncomeService {
    private final Connection connection;
    public IncomeServiceImpl() throws DaoException {
        this.connection = connectionPool.getConnection();
    }
    @Override
    public Income findEntityById(Integer id) throws ServiceException {
        try {
            return new IncomeDaoImpl(connection).findEntityById(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
