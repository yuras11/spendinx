package by.spendinx.dao;

import by.spendinx.entity.Operation;

import java.util.List;

public interface OperationDao extends Dao<Integer, Operation> {
    List<Operation> findOperationsByUserId(Integer id) throws DaoException;
}
