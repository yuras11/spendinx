import by.spendinx.dao.*;
import by.spendinx.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {
        Connection connection = ConnectionCreator.createConnection();
        UserDao userDao = new UserDaoImpl(connection);
        List<User> users = userDao.findAll();
        for (User r: users) {
            System.out.println(r);
        }

        CurrencyDao currencyDao = new CurrencyDaoImpl(connection);
        List<Currency> currencies = currencyDao.findAll();
        for (Currency rc: currencies) {
            System.out.println(rc);
        }

        ExpenditureItemDao expenditureItemDao = new ExpenditureItemDaoImpl(connection);
        List<ExpenditureItem> expenditureItems = expenditureItemDao.findAll();
        for (ExpenditureItem exi: expenditureItems) {
            System.out.println(exi);
        }

        ExpenditureDao expenditureDao = new ExpenditureDaoImpl(connection);
        List<Expenditure> expenditures = expenditureDao.findAll();
        for (Expenditure ex: expenditures) {
            System.out.println(ex);
        }

        IncomeSourceDao incomeSourceDao = new IncomeSourceDaoImpl(connection);
        List<IncomeSource> incomeSources = incomeSourceDao.findAll();
        for (IncomeSource ins: incomeSources) {
            System.out.println(ins);
        }

        IncomeDao incomeDao = new IncomeDaoImpl(connection);
        List<Income> incomes = incomeDao.findAll();
        for (Income in: incomes) {
            System.out.println(in);
        }

        OperationDao operationDao = new OperationDaoImpl(connection);
        List<Operation> operations = operationDao.findAll();
        for (Operation op: operations) {
            System.out.println(op);
        }
    }
}