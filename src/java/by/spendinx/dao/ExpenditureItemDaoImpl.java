package by.spendinx.dao;

import by.spendinx.entity.ExpenditureItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenditureItemDaoImpl implements ExpenditureItemDao {
    private static final String SQL_SELECT_ALL_EXPENDITURE_ITEMS =
            "SELECT ExpenditureItemId, Name FROM EXPENDITURE_ITEM";
    private static final String SQL_SELECT_EXPENDITURE_ITEM_BY_NAME =
            "SELECT ExpenditureItemId, Name FROM EXPENDITURE_ITEM WHERE Name=?";
    private static final String SQL_SELECT_EXPENDITURE_ITEM_BY_ID =
            "SELECT ExpenditureItemId, Name FROM EXPENDITURE_ITEM WHERE ExpenditureItemId=?";
    private static final String SQL_DELETE_EXPENDITURE_ITEM_BY_ID =
            "DELETE FROM EXPENDITURE_ITEM WHERE ExpenditureItemId=?";
    private Connection connection;

    public ExpenditureItemDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private List<ExpenditureItem> executeQueries(String query, String parameter)
    {
        List<ExpenditureItem> expenditureItems = new ArrayList<>();
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(query);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                ExpenditureItem expenditureItem = new ExpenditureItem(
                        resultSet.getInt("ExpenditureItemId"),
                        resultSet.getString("Name"));
                expenditureItems.add(expenditureItem);
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
        return expenditureItems;
    }

    @Override
    public List<ExpenditureItem> findAll() throws DaoException {
        //LOGGER.info("findAll() has been called");
        List<ExpenditureItem> expenditureItems = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_EXPENDITURE_ITEMS);
            while (resultSet.next())
            {
                ExpenditureItem expenditureItem = new ExpenditureItem(
                        resultSet.getInt("ExpenditureItemId"),
                        resultSet.getString("Name"));
                expenditureItems.add(expenditureItem);
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
        return expenditureItems;
    }

    @Override
    public ExpenditureItem findEntityById(Integer id) throws DaoException {
        return executeQueries(SQL_SELECT_EXPENDITURE_ITEM_BY_ID, id.toString()).get(0);
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(SQL_DELETE_EXPENDITURE_ITEM_BY_ID);
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
    public boolean create(ExpenditureItem expenditureItem) throws DaoException {
        Statement statement = null;
        String id = findAll().size() + 1 + "";
        String name = expenditureItem.getName();
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO EXPENDITURE_ITEM(ExpenditureItemId, Name) VALUES " +
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
    public boolean update(ExpenditureItem expenditureItem) throws DaoException {
        return true;
    }

    @Override
    public ExpenditureItem findExpenditureItemByName(String name) throws DaoException {
        return executeQueries(SQL_SELECT_EXPENDITURE_ITEM_BY_NAME, name).get(0);
    }
}
