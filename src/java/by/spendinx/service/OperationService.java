package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.OperationDao;
import by.spendinx.entity.Operation;
import by.spendinx.entity.User;
import org.testng.internal.collections.Pair;

import java.util.List;

public interface OperationService {
    Operation findEntityById(Integer id) throws ServiceException;
    List<Operation> findOperationsByUserId(Integer id) throws ServiceException;
    List<Pair<Float, String>> findSumOfIncomesInLastMonthByUserId(Integer id) throws ServiceException;
    List<Pair<Float, String>> findSumOfExpendituresInLastMonthByUserId(Integer id) throws ServiceException;
    List<Pair<Float, String>> findSumOfExpendituresByUserId(Integer id) throws ServiceException;
    List<Pair<Float, String>> findSumOfIncomesByUserId(Integer id) throws ServiceException;
    List<Pair<Float, String>> findSumOfExpendituresTodayByUserId(Integer id) throws ServiceException;
    List<Pair<Float, String>> findSumOfIncomesTodayByUserId(Integer id) throws ServiceException;
    boolean checkCreate(Operation operation) throws ServiceException;
    boolean checkDelete(Integer id) throws ServiceException;
    boolean checkUpdate(Operation operation) throws ServiceException;
}
