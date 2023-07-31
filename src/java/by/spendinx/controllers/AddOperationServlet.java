package by.spendinx.controllers;

import by.spendinx.dao.DaoException;
import by.spendinx.entity.Expenditure;
import by.spendinx.entity.Income;
import by.spendinx.entity.Operation;
import by.spendinx.entity.User;
import by.spendinx.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/addOperation")
public class AddOperationServlet extends HttpServlet {
    UserService userService;
    OperationService operationService;
    IncomeService incomeService;
    ExpenditureService expenditureService;
    IncomeSourceService incomeSourceService;
    ExpenditureItemService expenditureItemService;
    CurrencyService currencyService;

    {
        try {
            userService = new UserServiceImpl();
            operationService = new OperationServiceImpl();
            incomeService = new IncomeServiceImpl();
            expenditureService = new ExpenditureServiceImpl();
            incomeSourceService = new IncomeSourceServiceImpl();
            expenditureItemService = new ExpenditureItemServiceImpl();
            currencyService = new CurrencyServiceImpl();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {

            Integer id = Integer.parseInt(request.getParameter("UserId"));

            String type = request.getParameter("operation");
            String object = request.getParameter("object");

            Income income;
            Expenditure expenditure;
            if (Objects.equals(type, "income")) {
                int incomeSourceId = incomeSourceService.findIncomeSourceByName(object).getId();
                income = incomeService.findIncomeByIncomeSource(incomeSourceId);
                expenditure = null;
            } else {
                income = null;
                int expenditureItemId = expenditureItemService.findExpenditureItemByName(object).getId();
                expenditure = expenditureService.findExpenditureByExpenditureItem(expenditureItemId);
            }

            double volume = Double.parseDouble(request.getParameter("volume"));
            String currency = request.getParameter("currency");
            String dateOfOperation = request.getParameter("Date of operation");


            User user = userService.findEntityById(id);

            operationService.checkCreate(new Operation(1, user, income, expenditure, volume,
                    currencyService.findCurrencyByName(currency), dateOfOperation));
            List<Operation> operations = operationService.findOperationsByUserId(id);
            request.setAttribute("operations", operations);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
