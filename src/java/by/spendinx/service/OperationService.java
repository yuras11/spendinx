package by.spendinx.service;

import by.spendinx.entity.Operation;

public interface OperationService {
    Operation findEntityById(Integer id) throws ServiceException;
}
