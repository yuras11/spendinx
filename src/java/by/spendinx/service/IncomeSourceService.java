package by.spendinx.service;

import by.spendinx.entity.IncomeSource;

public interface IncomeSourceService {
    IncomeSource findEntityById(Integer id) throws ServiceException;
}
