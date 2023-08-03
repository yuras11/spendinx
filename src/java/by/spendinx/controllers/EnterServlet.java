package by.spendinx.controllers;

import by.spendinx.dao.DaoException;
import by.spendinx.entity.Operation;
import by.spendinx.entity.User;
import by.spendinx.service.OperationService;
import by.spendinx.service.OperationServiceImpl;
import by.spendinx.service.UserService;
import by.spendinx.service.UserServiceImpl;
import org.testng.internal.collections.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/enter")
public class EnterServlet extends HttpServlet {
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userLogin = request.getParameter("Login");
        String userPassword = request.getParameter("Password");
        try {
            User user = userService.findUserByLogin(userLogin);
            if (user != null && Objects.equals(userPassword, user.getPassword())) {
                List<Operation> operations = operationService.findOperationsByUserId(user.getId());
                List<Pair<Float, String>> expendituresInLastMonth = operationService.findSumOfExpendituresInLastMonthByUserId(user.getId());
                List<Pair<Float, String>> incomesInLastMonth = operationService.findSumOfIncomesInLastMonthByUserId(user.getId());
                List<Pair<Float, String>> expendituresAllTime = operationService.findSumOfExpendituresByUserId(user.getId());
                List<Pair<Float, String>> incomesAllTime = operationService.findSumOfIncomesByUserId(user.getId());
                List<Pair<Float, String>> expendituresToday = operationService.findSumOfExpendituresTodayByUserId(user.getId());
                List<Pair<Float, String>> incomesToday = operationService.findSumOfIncomesTodayByUserId(user.getId());

                request.setAttribute("user", user);
                request.setAttribute("operations", operations);

                request.setAttribute("expendituresInLastMonth", expendituresInLastMonth);
                request.setAttribute("incomesInLastMonth", incomesInLastMonth);

                request.setAttribute("expendituresAllTime", expendituresAllTime);
                request.setAttribute("incomesAllTime", incomesAllTime);

                request.setAttribute("expendituresToday", expendituresToday);
                request.setAttribute("incomesToday", incomesToday);

                getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}