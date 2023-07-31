package by.spendinx.controllers;

import by.spendinx.dao.DaoException;
import by.spendinx.entity.User;
import by.spendinx.service.UserService;
import by.spendinx.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    UserService userService;

    {
        try {
            userService = new UserServiceImpl();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("Name");
        String surname = request.getParameter("Surname");
        String dateOfBirth = request.getParameter("Date of birth");
        String login = request.getParameter("Login");
        String password = request.getParameter("Password");
        try {
            if (userService.checkCreate(new User(1, login, password, surname, name, dateOfBirth))) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/enter.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}