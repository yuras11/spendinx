package by.spendinx.controllers;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.OperationDaoImpl;
import by.spendinx.entity.Operation;
import by.spendinx.entity.User;
import by.spendinx.service.OperationService;
import by.spendinx.service.OperationServiceImpl;
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

@WebServlet("/deleteProfile")
public class DeleteProfileServlet extends HttpServlet {
    UserService userService;
    OperationService operationService;

    {
        try {
            userService = new UserServiceImpl();
            operationService = new OperationServiceImpl();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Integer id = Integer.parseInt(request.getParameter("UserId"));

        try {
            List<Operation> operations = operationService.findOperationsByUserId(id);
            if (!operations.isEmpty()) {
                for (Operation operation: operations) {
                    operationService.checkDelete(operation.getId());
                }
            }
            userService.checkDelete(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/deleteProfile.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
