package by.spendinx.dao;

import by.spendinx.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDaoImpl implements OperationDao {
    private static final String SQL_SELECT_ALL_OPERATIONS =
            "SELECT OperationId, [User], Income, Expenditure, Volume, Currency, DateOfOperation FROM OPERATION";
    private static final String SQL_SELECT_OPERATION_BY_USER_ID =
            "SELECT OperationId, [User], Income, Expenditure, Volume, Currency, DateOfOperation FROM OPERATION WHERE User=?";
    private static final String SQL_SELECT_OPERATION_BY_ID =
            "SELECT OperationId, [User], Income, Expenditure, Volume, Currency, DateOfOperation FROM OPERATION WHERE OperationId=?";
    private static final String SQL_DELETE_OPERATION_BY_ID =
            "DELETE FROM OPERATION WHERE OperationId=?";
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
        String id = findAll().size() + 1 + "";
        String user = operation.getUser().getId() + "";
        String income = operation.getIncome().getId() + "";
        String expenditure = operation.getExpenditure().getId() + "";
        String volume = operation.getVolume() + "";
        String currency = operation.getCurrency().getId() + "";
        String dateOfOperation = operation.getDateOfOperation();
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO OPERATION(OperationId, User, Income, Expenditure, Volume, Currency, DateOfOperation) VALUES " +
                    "(" + id + ", "+ "\'" + user + "\'" + ", "+ "\'" + income + "\'" + ", " + "\'" + expenditure + "\'" + ", " +
                    "\'" + volume + "\'" + ", " + "\'" + currency + "\'" + ", " + "\'" + dateOfOperation + "\'" + ")");
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
}
