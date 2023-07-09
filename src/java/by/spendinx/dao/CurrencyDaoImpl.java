package by.spendinx.dao;

import by.spendinx.entity.Currency;
import by.spendinx.entity.Entity;
import by.spendinx.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CurrencyDaoImpl implements CurrencyDao {
    private static final String SQL_SELECT_ALL_CURRENCIES =
            "SELECT CurrencyId, Name, Course FROM CURRENCY";
    private static final String SQL_SELECT_CURRENCY_BY_NAME =
            "SELECT CurrencyId, Name, Course FROM CURRENCY WHERE Name=?";
    private static final String SQL_SELECT_CURRENCY_BY_ID =
            "SELECT CurrencyId, Name, Course FROM CURRENCY WHERE CurrencyId=?";
    private static final String SQL_DELETE_CURRENCY_BY_ID =
            "DELETE FROM CURRENCY WHERE CurrencyId=?";
    private Connection connection;

    public CurrencyDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private List<Currency> executeQueries(String query, String parameter)
    {
        List<Currency> currencies = new ArrayList<>();
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(query);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Currency currency = new Currency(
                        resultSet.getInt("CurrencyId"),
                        resultSet.getString("Name"),
                        resultSet.getFloat("Course"));
                currencies.add(currency);
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
        return currencies;
    }

    @Override
    public Currency findCurrencyByName(String name) {
        return executeQueries(SQL_SELECT_CURRENCY_BY_NAME, name).get(0);
    }

    @Override
    public boolean updateCourse(Currency currency, float course) {
        //LOGGER.info("updateLogin() has been called");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE CURRENCY SET Course = " + "\'" + course + "\'" + " WHERE CurrencyId = " + currency.getId());
            currency.setCourse(course);
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in updateLogin()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public List<Currency> findAll() throws DaoException {
        //LOGGER.info("findAll() has been called");
        List<Currency> currencies = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CURRENCIES);
            while (resultSet.next())
            {
                Currency currency = new Currency(
                        resultSet.getInt("CurrencyId"),
                        resultSet.getString("Name"),
                        resultSet.getFloat("Course"));
                currencies.add(currency);
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
        return currencies;
    }

    @Override
    public Currency findEntityById(Integer id) throws DaoException {
        return executeQueries(SQL_SELECT_CURRENCY_BY_ID, id.toString()).get(0);
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(SQL_DELETE_CURRENCY_BY_ID);
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
    public boolean create(Currency currency) throws DaoException {
        Statement statement = null;
        String id = findAll().size() + 1 + "";
        String name = currency.getName();
        String course = currency.getCourse() + "";
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO CURRENCY(CurrencyId, Name, Course) VALUES " +
                    "(" + id + ", "+ "\'" + name + "\'" + ", "+ "\'" + course + "\'" + ")");
        }
        catch (SQLException e)
        {
            //LOGGER.log(Level.WARNING, "An error occurred in create()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean update(Currency currency) throws DaoException {
        //LOGGER.info("update() has been called");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE CURRENCY SET Course = Course + 0.1 WHERE CurrencyId = " + currency.getId());
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in update()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }
}
