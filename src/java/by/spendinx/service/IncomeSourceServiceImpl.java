package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.IncomeSourceDaoImpl;
import by.spendinx.entity.IncomeSource;

import java.sql.Connection;

public class IncomeSourceServiceImpl extends ServiceImpl implements IncomeSourceService {
    private final Connection connection;
    public IncomeSourceServiceImpl() throws DaoException {
        this.connection = connectionPool.getConnection();
    }
    @Override
    public IncomeSource findEntityById(Integer id) throws ServiceException {
        try {
            return new IncomeSourceDaoImpl(connection).findEntityById(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public IncomeSource findIncomeSourceByName(String name) throws ServiceException {
        try {
            return new IncomeSourceDaoImpl(connection).findIncomeSourceByName(name);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
