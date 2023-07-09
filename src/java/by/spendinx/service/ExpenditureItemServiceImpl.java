package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.ExpenditureItemDaoImpl;
import by.spendinx.entity.ExpenditureItem;

import java.sql.Connection;

public class ExpenditureItemServiceImpl extends ServiceImpl implements ExpenditureItemService {
    private final Connection connection;
    public ExpenditureItemServiceImpl() throws DaoException {
        this.connection = connectionPool.getConnection();
    }
    @Override
    public ExpenditureItem findEntityById(Integer id) throws ServiceException {
        try {
            return new ExpenditureItemDaoImpl(connection).findEntityById(id);
        }
        catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
