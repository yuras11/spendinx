package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.OperationDaoImpl;
import by.spendinx.dao.UserDaoImpl;
import by.spendinx.entity.Operation;
import org.testng.internal.collections.Pair;

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
    public List<Pair<Float, String>> findSumOfIncomesInLastMonthByUserId(Integer id) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).findSumOfIncomesInLastMonthByUserId(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Pair<Float, String>> findSumOfExpendituresInLastMonthByUserId(Integer id) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).findSumOfExpendituresInLastMonthByUserId(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Pair<Float, String>> findSumOfExpendituresByUserId(Integer id) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).findSumOfExpendituresByUserId(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Pair<Float, String>> findSumOfIncomesByUserId(Integer id) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).findSumOfIncomesByUserId(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Pair<Float, String>> findSumOfExpendituresTodayByUserId(Integer id) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).findSumOfExpendituresTodayByUserId(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Pair<Float, String>> findSumOfIncomesTodayByUserId(Integer id) throws ServiceException {
        try {
            return new OperationDaoImpl(connection).findSumOfIncomesTodayByUserId(id);
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
