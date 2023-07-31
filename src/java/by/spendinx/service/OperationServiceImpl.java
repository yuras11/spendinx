package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.OperationDaoImpl;
import by.spendinx.dao.UserDaoImpl;
import by.spendinx.entity.Operation;

import java.sql.Connection;
import java.util.List;

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

    @Override
    public List<Operation> findOperationsByUserId(Integer id) throws ServiceException {
        try  {
            return new OperationDaoImpl(connection).findOperationsByUserId(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkCreate(Operation operation) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).create(operation);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkDelete(Integer id) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkUpdate(Operation operation) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).update(operation);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
