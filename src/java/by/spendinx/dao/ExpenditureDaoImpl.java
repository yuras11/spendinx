package by.spendinx.dao;

import by.spendinx.entity.Expenditure;
import by.spendinx.entity.ExpenditureItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenditureDaoImpl implements ExpenditureDao {
    private static final String SQL_SELECT_ALL_EXPENDITURES =
            "SELECT ExpenditureId, Name, ExpenditureItem FROM EXPENDITURE";
    private static final String SQL_SELECT_EXPENDITURE_BY_NAME =
            "SELECT ExpenditureId, Name, ExpenditureItem FROM EXPENDITURE WHERE Name=?";
    private static final String SQL_SELECT_EXPENDITURE_BY_ID =
            "SELECT ExpenditureId, Name, ExpenditureItem FROM EXPENDITURE WHERE ExpenditureId=?";
    private static final String SQL_DELETE_EXPENDITURE_BY_ID =
            "DELETE FROM EXPENDITURE WHERE ExpenditureId=?";
    private static final String SQL_SELECT_EXPENDITURE_BY_EXPENDITURE_ITEM =
            "SELECT ExpenditureId, Name, ExpenditureItem FROM EXPENDITURE WHERE ExpenditureItem=?";
    private Connection connection;

    public ExpenditureDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private List<Expenditure> executeQueries(String query, String parameter) throws DaoException
    {
        List<Expenditure> expenditures = new ArrayList<>();
        ExpenditureItemDao expenditureItemDao = new ExpenditureItemDaoImpl(connection);
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(query);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Expenditure expenditure = new Expenditure(
                        resultSet.getInt("ExpenditureId"),
                        resultSet.getString("Name"),
                        expenditureItemDao.findEntityById(resultSet.getInt("ExpenditureItem"))
                        );
                expenditures.add(expenditure);
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
        return expenditures;
    }

    @Override
    public List<Expenditure> findAll() throws DaoException {
        //LOGGER.info("findAll() has been called");
        List<Expenditure> expenditures = new ArrayList<>();
        ExpenditureItemDao expenditureItemDao = new ExpenditureItemDaoImpl(connection);
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_EXPENDITURES);
            while (resultSet.next())
            {
                Expenditure expenditure = new Expenditure(
                        resultSet.getInt("ExpenditureId"),
                        resultSet.getString("Name"),
                        expenditureItemDao.findEntityById(resultSet.getInt("ExpenditureItem"))
                );
                expenditures.add(expenditure);
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
        return expenditures;
    }

    @Override
    public Expenditure findEntityById(Integer id) throws DaoException {
        return executeQueries(SQL_SELECT_EXPENDITURE_BY_ID, id.toString()).get(0);
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(SQL_DELETE_EXPENDITURE_BY_ID);
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
    public boolean create(Expenditure expenditure) throws DaoException {
        Statement statement = null;
        String id = findAll().size() + 1 + "";
        String name = expenditure.getName();
        String expenditureItem = expenditure.getExpenditureItem().getId() + "";
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO EXPENDITURE(ExpenditureId, Name, ExpenditureItem) VALUES " +
                    "(" + id + ", "+ "\'" + name + "\'" + ", "+ "\'" + expenditureItem + "\'" + ")");
        }
        catch (SQLException e)
        {
            //LOGGER.log(Level.WARNING, "An error occurred in create()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean update(Expenditure expenditure) throws DaoException {
        return true;
    }

    @Override
    public Expenditure findExpenditureByExpenditureItem(Integer id) throws DaoException {
        return executeQueries(SQL_SELECT_EXPENDITURE_BY_EXPENDITURE_ITEM, id.toString()).get(0);
    }
}
