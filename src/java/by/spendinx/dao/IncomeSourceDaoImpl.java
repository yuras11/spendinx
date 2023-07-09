package by.spendinx.dao;

import by.spendinx.entity.ExpenditureItem;
import by.spendinx.entity.IncomeSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeSourceDaoImpl implements IncomeSourceDao {
    private static final String SQL_SELECT_ALL_INCOME_SOURCES =
            "SELECT IncomeSourceId, Name FROM INCOME_SOURCE";
    private static final String SQL_SELECT_INCOME_SOURCE_BY_NAME =
            "SELECT IncomeSourceId, Name FROM INCOME_SOURCE WHERE Name=?";
    private static final String SQL_SELECT_INCOME_SOURCE_BY_ID =
            "SELECT IncomeSourceId, Name FROM INCOME_SOURCE WHERE IncomeSourceId=?";
    private static final String SQL_DELETE_INCOME_SOURCE_BY_ID =
            "DELETE FROM INCOME_SOURCE WHERE IncomeSourceId=?";
    private Connection connection;

    public IncomeSourceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private List<IncomeSource> executeQueries(String query, String parameter)
    {
        List<IncomeSource> incomeSources = new ArrayList<>();
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(query);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                IncomeSource incomeSource = new IncomeSource(
                        resultSet.getInt("IncomeSourceId"),
                        resultSet.getString("Name"));
                incomeSources.add(incomeSource);
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
        return incomeSources;
    }

    @Override
    public List<IncomeSource> findAll() throws DaoException {
        //LOGGER.info("findAll() has been called");
        List<IncomeSource> incomeSources = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_INCOME_SOURCES);
            while (resultSet.next())
            {
                IncomeSource incomeSource = new IncomeSource(
                        resultSet.getInt("IncomeSourceId"),
                        resultSet.getString("Name"));
                incomeSources.add(incomeSource);
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
        return incomeSources;
    }

    @Override
    public IncomeSource findEntityById(Integer id) throws DaoException {
        return executeQueries(SQL_SELECT_INCOME_SOURCE_BY_ID, id.toString()).get(0);
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(SQL_DELETE_INCOME_SOURCE_BY_ID);
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
    public boolean create(IncomeSource incomeSource) throws DaoException {
        Statement statement = null;
        String id = findAll().size() + 1 + "";
        String name = incomeSource.getName();
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO INCOME_SOURCE(IncomeSourceId, Name) VALUES " +
                    "(" + id + ", "+ "\'" + name + "\'" + ")");
        }
        catch (SQLException e)
        {
            //LOGGER.log(Level.WARNING, "An error occurred in create()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean update(IncomeSource incomeSource) throws DaoException {
        return true;
    }
}
