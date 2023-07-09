package by.spendinx.dao;

import by.spendinx.entity.Income;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeDaoImpl implements IncomeDao {
    private static final String SQL_SELECT_ALL_INCOMES =
            "SELECT IncomeId, Name, IncomeSource FROM INCOME";
    private static final String SQL_SELECT_INCOME_BY_NAME =
            "SELECT IncomeId, Name, IncomeSource FROM INCOME WHERE Name=?";
    private static final String SQL_SELECT_INCOME_BY_ID =
            "SELECT IncomeId, Name, IncomeSource FROM INCOME WHERE IncomeId=?";
    private static final String SQL_DELETE_INCOME_BY_ID =
            "DELETE FROM INCOME WHERE IncomeId=?";
    private Connection connection;

    public IncomeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private List<Income> executeQueries(String query, String parameter) throws DaoException
    {
        List<Income> incomes = new ArrayList<>();
        IncomeSourceDao incomeSourceDao = new IncomeSourceDaoImpl(connection);
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(query);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Income income = new Income(
                        resultSet.getInt("IncomeId"),
                        resultSet.getString("Name"),
                        incomeSourceDao.findEntityById(resultSet.getInt("IncomeSource"))
                );
                incomes.add(income);
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
        return incomes;
    }

    @Override
    public List<Income> findAll() throws DaoException {
        //LOGGER.info("findAll() has been called");
        List<Income> incomes = new ArrayList<>();
        IncomeSourceDao incomeSourceDao = new IncomeSourceDaoImpl(connection);
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_INCOMES);
            while (resultSet.next())
            {
                Income income = new Income(
                        resultSet.getInt("IncomeId"),
                        resultSet.getString("Name"),
                        incomeSourceDao.findEntityById(resultSet.getInt("IncomeSource"))
                );
                incomes.add(income);
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
        return incomes;
    }

    @Override
    public Income findEntityById(Integer id) throws DaoException {
        return executeQueries(SQL_SELECT_INCOME_BY_ID, id.toString()).get(0);
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(SQL_DELETE_INCOME_BY_ID);
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
    public boolean create(Income income) throws DaoException {
        Statement statement = null;
        String id = findAll().size() + 1 + "";
        String name = income.getName();
        String incomeSource = income.getIncomeSource().getId() + "";
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO INCOME(IncomeId, Name, IncomeSource) VALUES " +
                    "(" + id + ", "+ "\'" + name + "\'" + ", "+ "\'" + incomeSource + "\'" + ")");
        }
        catch (SQLException e)
        {
            //LOGGER.log(Level.WARNING, "An error occurred in create()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean update(Income income) throws DaoException {
        return true;
    }
}
