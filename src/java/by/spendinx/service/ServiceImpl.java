package by.spendinx.service;

import by.spendinx.dao.DaoException;
import by.spendinx.dao.pool.ConnectionPool;

abstract public class ServiceImpl {
    public static final String DB_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DB_URL = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;user=TestUser;password=password;databaseName=spendinx";
    public static final String DB_USER = "TestUser";
    public static final String DB_PASSWORD = "password";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;
    ConnectionPool connectionPool = new ConnectionPool(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);

    protected ServiceImpl() throws DaoException {
    }
}
