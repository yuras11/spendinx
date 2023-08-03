package by.spendinx.dao;

import by.spendinx.entity.*;
import org.testng.internal.collections.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OperationDaoImpl implements OperationDao {
    private static final String SQL_SELECT_ALL_OPERATIONS =
            "SELECT OperationId, [User], Income, Expenditure, Volume, Currency, DateOfOperation FROM OPERATION";
    private static final String SQL_SELECT_OPERATIONS_BY_USER_ID =
            "SELECT OperationId, [User], Income, Expenditure, Volume, Currency, DateOfOperation FROM OPERATION WHERE [User]=?";
    private static final String SQL_SELECT_OPERATION_BY_ID =
            "SELECT OperationId, [User], Income, Expenditure, Volume, Currency, DateOfOperation FROM OPERATION WHERE OperationId=?";
    private static final String SQL_DELETE_OPERATION_BY_ID =
            "DELETE FROM OPERATION WHERE OperationId=?";
    private static final String SQL_SELECT_EXPENDITURES_IN_LAST_MONTH_BY_USER_ID =
            "SELECT * FROM OPERATION WHERE Income = 0 AND DateOfOperation >= DATEADD(MONTH, -1, GETDATE()) AND [User]=?";
    private static final String SQL_SELECT_INCOMES_IN_LAST_MONTH_BY_USER_ID =
            "SELECT * FROM OPERATION WHERE Expenditure = 0 AND DateOfOperation >= DATEADD(MONTH, -1, GETDATE()) AND [User]=?";
    private static final String SQL_SELECT_EXPENDITURES_TODAY_BY_USER_ID =
            "SELECT * FROM OPERATION WHERE Income = 0 AND CAST(DateOfOperation AS DATE) = CAST(GETDATE() AS DATE) AND [User]=?";
    private static final String SQL_SELECT_INCOMES_TODAY_BY_USER_ID =
            "SELECT * FROM OPERATION WHERE Expenditure = 0 AND CAST(DateOfOperation AS DATE) = CAST(GETDATE() AS DATE) AND [User]=?";
    private static final String SQL_SELECT_INCOMES_BY_USER_ID =
            "SELECT * FROM OPERATION WHERE Expenditure = 0 AND [User]=?";
    private static final String SQL_SELECT_EXPENDITURES_BY_USER_ID =
            "SELECT * FROM OPERATION WHERE Income = 0 AND [User]=?";
    private Connection connection;

    public OperationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private List<Operation> executeQueries(String query, String parameter) throws DaoException
    {
        List<Operation> operations = new ArrayList<>();
        UserDao userDao = new UserDaoImpl(connection);
        IncomeDao incomeDao = new IncomeDaoImpl(connection);
        ExpenditureDao expenditureDao = new ExpenditureDaoImpl(connection);
        CurrencyDao currencyDao = new CurrencyDaoImpl(connection);
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(query);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next())
            {
                Income income;
                Expenditure expenditure;
                if (resultSet.getInt("Income") == 0) {
                    income = null;
                    expenditure = expenditureDao.findEntityById(resultSet.getInt("Expenditure"));
                }
                else {
                    income = incomeDao.findEntityById(resultSet.getInt("Income"));
                    expenditure = null;
                }
                Operation operation = new Operation(
                        resultSet.getInt("OperationId"),
                        userDao.findEntityById(resultSet.getInt("User")),
                        income, expenditure,
                        resultSet.getFloat("Volume"),
                        currencyDao.findEntityById(resultSet.getInt("Currency")),
                        resultSet.getString("DateOfOperation")
                        );
                operations.add(operation);
            }

        }
        catch (SQLException e)
        {
            // LOGGER.log(Level.WARNING, "An error occurred in executeQueries()!");
            throw new UnsupportedOperationException();
        }
        finally
        {
            close(statement);
        }
        return operations;
    }

    @Override
    public List<Operation> findAll() throws DaoException {
        //LOGGER.info("findAll() has been called");
        List<Operation> operations = new ArrayList<>();
        UserDao userDao = new UserDaoImpl(connection);
        IncomeDao incomeDao = new IncomeDaoImpl(connection);
        ExpenditureDao expenditureDao = new ExpenditureDaoImpl(connection);
        CurrencyDao currencyDao = new CurrencyDaoImpl(connection);
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_OPERATIONS);

            while (resultSet.next())
            {
                Income income;
                Expenditure expenditure;
                if (resultSet.getInt("Income") == 0) {
                    income = null;
                    expenditure = expenditureDao.findEntityById(resultSet.getInt("Expenditure"));
                }
                else {
                    income = incomeDao.findEntityById(resultSet.getInt("Income"));
                    expenditure = null;
                }
                Operation operation = new Operation(
                        resultSet.getInt("OperationId"),
                        userDao.findEntityById(resultSet.getInt("User")),
                        income, expenditure,
                        resultSet.getFloat("Volume"),
                        currencyDao.findEntityById(resultSet.getInt("Currency")),
                        resultSet.getString("DateOfOperation")
                );
                operations.add(operation);
            }

        }
        catch (SQLException e)
        {
            //LOGGER.log(Level.WARNING, "An error occurred in findAll()!");
            throw new UnsupportedOperationException();
        }
        finally
        {
            close(statement);
        }
        return operations;
    }

    @Override
    public Operation findEntityById(Integer id) throws DaoException {
        return executeQueries(SQL_SELECT_OPERATION_BY_ID, id.toString()).get(0);
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(SQL_DELETE_OPERATION_BY_ID);
            statement.setString(1, id.toString());
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            //LOGGER.log(Level.WARNING, "An error occurred in delete()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean create(Operation operation) throws DaoException {
        Statement statement = null;
        int id = findAll().size() + 1;
        int user = operation.getUser().getId();
        int income;
        int expenditure;
        if (operation.getIncome() == null) {
            income = 0;
            expenditure = operation.getExpenditure().getId();
        } else {
            income = operation.getIncome().getId();
            expenditure = 0;
        }
        double volume = operation.getVolume();
        int currency = operation.getCurrency().getId();
        String dateOfOperation = operation.getDateOfOperation();
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO OPERATION(OperationId, [User], Income, Expenditure, Volume, Currency, DateOfOperation) VALUES " +
                    "(" + id + ", " + user + ", "+ income + ", " + expenditure + ", " +
                    volume + ", " + currency + ", " + "\'" + dateOfOperation + "\'" + ")");
        }
        catch (SQLException e)
        {
            //LOGGER.log(Level.WARNING, "An error occurred in create()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean update(Operation operation) throws DaoException {
        return true;
    }

    @Override
    public List<Operation> findOperationsByUserId(Integer id) throws DaoException {
        return executeQueries(SQL_SELECT_OPERATIONS_BY_USER_ID, id.toString());
    }

    @Override
    public List<Pair<Float, String>> findSumOfExpendituresInLastMonthByUserId(Integer id) throws DaoException {
        return findSums(SQL_SELECT_EXPENDITURES_IN_LAST_MONTH_BY_USER_ID, id);
    }

    @Override
    public List<Pair<Float, String>> findSumOfIncomesInLastMonthByUserId(Integer id) throws DaoException {
        return findSums(SQL_SELECT_INCOMES_IN_LAST_MONTH_BY_USER_ID, id);
    }

    @Override
    public List<Pair<Float, String>> findSumOfExpendituresByUserId(Integer id) throws DaoException {
        return findSums(SQL_SELECT_EXPENDITURES_BY_USER_ID, id);
    }

    @Override
    public List<Pair<Float, String>> findSumOfIncomesByUserId(Integer id) throws DaoException {
        return findSums(SQL_SELECT_INCOMES_BY_USER_ID, id);
    }

    @Override
    public List<Pair<Float, String>> findSumOfExpendituresTodayByUserId(Integer id) throws DaoException {
        return findSums(SQL_SELECT_EXPENDITURES_TODAY_BY_USER_ID, id);
    }

    @Override
    public List<Pair<Float, String>> findSumOfIncomesTodayByUserId(Integer id) throws DaoException {
        return findSums(SQL_SELECT_INCOMES_TODAY_BY_USER_ID, id);
    }

    private List<Pair<Float, String>> findSums(String query, Integer id) throws DaoException {
        CurrencyDao currencyDao = new CurrencyDaoImpl(connection);
        List<Currency> currencies = currencyDao.findAll();
        List<Pair<Float, String>> pairs = new ArrayList<>();
        List<Operation> operations = executeQueries(query, id.toString());
        for (Currency currency: currencies) {
            float sum = 0;
            String curr = "";
            for (Operation operation: operations) {
                if (Objects.equals(operation.getCurrency().getName(), currency.getName())) {
                    sum += operation.getVolume();
                    curr = operation.getCurrency().getName();
                }
            }
            if (sum != 0) {
                pairs.add(new Pair<>(sum, curr));
            }
        }
        return pairs;
    }

}
