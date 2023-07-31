package by.spendinx.service;

import by.spendinx.dao.OperationDao;
import by.spendinx.entity.Operation;
import by.spendinx.entity.User;

import java.util.List;

public interface OperationService {
    Operation findEntityById(Integer id) throws ServiceException;
    List<Operation> findOperationsByUserId(Integer id) throws ServiceException;
    boolean checkCreate(Operation operation) throws ServiceException;
    boolean checkDelete(Integer id) throws ServiceException;
    boolean checkUpdate(Operation operation) throws ServiceException;
}
