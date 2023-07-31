package by.spendinx.service;

import by.spendinx.dao.CurrencyDaoImpl;
import by.spendinx.dao.DaoException;
import by.spendinx.entity.Currency;

import java.sql.Connection;

public class CurrencyServiceImpl extends ServiceImpl implements CurrencyService {
    private final Connection connection;
    public CurrencyServiceImpl() throws DaoException {
        this.connection = connectionPool.getConnection();
    }

    @Override
    public Currency findCurrencyByName(String name) throws ServiceException {
        return new CurrencyDaoImpl(connection).findCurrencyByName(name);
    }

    @Override
    public Currency findEntityById(Integer id) throws ServiceException {
        try {
            return new CurrencyDaoImpl(connection).findEntityById(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateCourse(Currency currency, float course) {
        return new CurrencyDaoImpl(connection).updateCourse(currency, course);
    }
}
