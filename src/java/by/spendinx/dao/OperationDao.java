package by.spendinx.dao;

import by.spendinx.entity.Operation;
import org.testng.internal.collections.Pair;

import java.util.List;

public interface OperationDao extends Dao<Integer, Operation> {
    List<Operation> findOperationsByUserId(Integer id) throws DaoException;
    List<Pair<Float, String>> findSumOfExpendituresInLastMonthByUserId(Integer id) throws DaoException;
    List<Pair<Float, String>> findSumOfIncomesInLastMonthByUserId(Integer id) throws DaoException;
    List<Pair<Float, String>> findSumOfExpendituresByUserId(Integer id) throws DaoException;
    List<Pair<Float, String>> findSumOfIncomesByUserId(Integer id) throws DaoException;
    List<Pair<Float, String>> findSumOfExpendituresTodayByUserId(Integer id) throws DaoException;
    List<Pair<Float, String>> findSumOfIncomesTodayByUserId(Integer id) throws DaoException;
}
