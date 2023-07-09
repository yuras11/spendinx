package by.spendinx.dao;

import by.spendinx.entity.User;

import java.util.List;

public interface UserDao extends Dao<Integer, User>
{
    List<User> findUserBySurname(String surname) throws DaoException;
    User findUserByLogin(String login) throws DaoException;

    boolean updateLogin(User user, String login);
    boolean updatePassword(User user, String password);
    boolean updateName(User user, String name);
    boolean updateSurname(User user, String surname);
    boolean updateDateOfBirth(User user, String dateOfBirth);

}
