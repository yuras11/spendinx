package by.spendinx.dao;

import by.spendinx.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserDaoImpl implements UserDao {
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT UserId, Login, Password, Surname, Name, DateOfBirth FROM [USER]";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT UserId, Login, Password, Surname, Name, DateOfBirth FROM [USER] WHERE UserId=?";
    private static final String SQL_SELECT_USER_BY_LOGIN =
            "SELECT UserId, Login, Password, Surname, Name, DateOfBirth FROM [USER] WHERE Login=?";
    private static final String SQL_SELECT_USER_BY_SURNAME =
            "SELECT UserId, Login, Password, Surname, Name, DateOfBirth FROM [USER] WHERE Surname=?";
    private static final String SQL_DELETE_USER_BY_ID =
            "DELETE FROM [USER] WHERE UserId =?";
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    private List<User> executeQueries(String query, String parameter)
    {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(query);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                User user = new User(resultSet.getInt("UserId"),
                        outputFormatting(resultSet.getString("Login")),
                        outputFormatting(resultSet.getString("Password")),
                        outputFormatting(resultSet.getString("Surname")),
                        outputFormatting(resultSet.getString("Name")),
                        outputFormatting(resultSet.getString("DateOfBirth")));
                users.add(user);
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
        return users;
    }
    @Override
    public List<User> findAll() throws DaoException {
        //LOGGER.info("findAll() has been called");
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try
        {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next())
            {
                User user = new User(resultSet.getInt("UserId"),
                        outputFormatting(resultSet.getString("Login")),
                        outputFormatting(resultSet.getString("Password")),
                        outputFormatting(resultSet.getString("Surname")),
                        outputFormatting(resultSet.getString("Name")),
                        outputFormatting(resultSet.getString("DateOfBirth")));
                users.add(user);
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
        return users;
    }

    @Override
    public User findEntityById(Integer id) throws DaoException {
        //LOGGER.info("findEntityById() has been called");
        return executeQueries(SQL_SELECT_USER_BY_ID, id.toString()).get(0);
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        //LOGGER.info("delete() has been called");
        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
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
    public boolean create(User user) throws DaoException {
        //LOGGER.info("create() has been called");
        Statement statement = null;
        String id = findAll().size() + 1 + "";
        String login = user.getLogin();
        String password = user.getPassword();
        String userSurname = user.getSurname();
        String userName = user.getName();
        String dateOfBirth = user.getDateOfBirth();
        try
        {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO [USER] VALUES " +
                    "(" + id + ", " + "\'" + login + "\'" + ", " +
                    "\'" +password + "\'" + ", " + "\'" + userSurname + "\'" + ", " +
                    "\'" + userName + "\'" + ", " + "\'" + dateOfBirth + "\'" + ")" );
        }
        catch (SQLException e)
        {
            //LOGGER.log(Level.WARNING, "An error occurred in create()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean update(User user) throws DaoException {
        //LOGGER.info("update() has been called");
        return true;
    }

    @Override
    public List<User> findUserBySurname(String surname) throws DaoException {
        //LOGGER.info("findUserBySurname() has been called");
        return executeQueries(SQL_SELECT_USER_BY_SURNAME, surname);
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        //LOGGER.info("findUserByLogin() has been called");
        return executeQueries(SQL_SELECT_USER_BY_LOGIN, login).get(0);
    }

    @Override
    public boolean updateLogin(User user, String login) {
        //LOGGER.info("updateLogin() has been called");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE [USER] SET Login = " + "\'" + login + "\'" + " WHERE UserId = " + user.getId());
            user.setLogin(login);
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in updateLogin()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean updatePassword(User user, String password) {
        //LOGGER.info("updatePassword() has been called");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE [USER] SET Password = " + "\'" + password + "\'" + " WHERE UserId = " + user.getId());
            user.setPassword(password);
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in updatePassword()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean updateName(User user, String name) {
        //LOGGER.info("updateLogin() has been called");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE [USER] SET Name = " + "\'" + name + "\'" + " WHERE UserId = " + user.getId());
            user.setName(name);
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in updateLogin()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean updateSurname(User user, String surname) {
        //LOGGER.info("updateLogin() has been called");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE [USER] SET Surname = " + "\'" + surname + "\'" + " WHERE UserId = " + user.getId());
            user.setSurname(surname);
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in updateLogin()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    @Override
    public boolean updateDateOfBirth(User user, String dateOfBirth) {
        //LOGGER.info("updateLogin() has been called");
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE [USER] SET DateOfBirth = " + "\'" + dateOfBirth + "\'" + " WHERE UserId = " + user.getId());
            user.setDateOfBirth(dateOfBirth);
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in updateLogin()!");
            throw new UnsupportedOperationException();
        }
        return true;
    }

    private String outputFormatting(String str){
        //LOGGER.info("outputFormatting() has been called");
        return str.replaceAll(" ", "").replaceAll("_", " ");
    }
}
