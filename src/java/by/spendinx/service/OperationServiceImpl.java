package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.OperationDaoImpl;
import by.spendinx.entity.Operation;

import java.sql.Connection;

public class OperationServiceImpl extends ServiceImpl implements OperationService {
    private final Connection connection;
    public OperationServiceImpl() throws DaoException {
        this.connection = connectionPool.getConnection();
    }
    @Override
    public Operation findEntityById(Integer id) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).findEntityById(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
