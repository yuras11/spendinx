package by.spendinx.service;

import by.spendinx.entity.User;

import java.util.List;

public interface UserService extends Service {
    List<User> findUserBySurname(String surname) throws ServiceException;
    User findUserByLogin(String login) throws ServiceException;
    User findEntityById(Integer id) throws ServiceException;

    boolean updateLogin(User user, String login);
    boolean updatePassword(User user, String password);
    boolean updateName(User user, String name);
    boolean updateSurname(User user, String surname);
    boolean updateDateOfBirth(User user, String dateOfBirth);
}
