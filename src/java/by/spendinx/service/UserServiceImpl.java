package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.UserDaoImpl;
import by.spendinx.entity.User;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;

public class UserServiceImpl extends ServiceImpl implements UserService {
    private final Connection connection;

    public UserServiceImpl() throws DaoException {
        this.connection = connectionPool.getConnection();
    }
    @Override
    public List<User> findUserBySurname(String surname) throws ServiceException {
        try {
            //LOGGER.info("findUserBySurname() has been called");
            return new UserDaoImpl(connection).findUserBySurname(surname);
        } catch (DaoException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in findUserBySurname()!");
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserByLogin(String login) throws ServiceException {
        try {
            //LOGGER.info("findUserBySurname() has been called");
            return new UserDaoImpl(connection).findUserByLogin(login);
        } catch (DaoException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in findUserBySurname()!");
            throw new ServiceException(e);
        }
    }

    @Override
    public User findEntityById(Integer id) throws ServiceException {
        try {
            //LOGGER.info("findUserBySurname() has been called");
            return new UserDaoImpl(connection).findEntityById(id);
        } catch (DaoException e) {
            //LOGGER.log(Level.WARNING, "An error occurred in findUserBySurname()!");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateLogin(User user, String login) {
        return new UserDaoImpl(connection).updateLogin(user, login);
    }

    @Override
    public boolean updatePassword(User user, String password) {
        return new UserDaoImpl(connection).updatePassword(user, password);
    }

    @Override
    public boolean updateName(User user, String name) {
        return new UserDaoImpl(connection).updateName(user, name);
    }

    @Override
    public boolean updateSurname(User user, String surname) {
        return new UserDaoImpl(connection).updateSurname(user, surname);
    }

    @Override
    public boolean updateDateOfBirth(User user, String dateOfBirth) {
        return new UserDaoImpl(connection).updateDateOfBirth(user, dateOfBirth);
    }

    public boolean checkCreate(User user) throws ServiceException {
        try {
            return new UserDaoImpl(connection).create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkDelete(Integer id) throws ServiceException {
        try {
            return new UserDaoImpl(connection).delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkUpdate(User user) throws ServiceException {
        try {
            return new UserDaoImpl(connection).update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
