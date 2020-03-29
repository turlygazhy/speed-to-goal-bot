package dao;

import connection_pool.ConnectionPool;
import dao.impl.ButtonDao;
import dao.impl.KeyboardMarkUpDao;
import dao.impl.MessageDao;
import dao.impl.ResultDao;

import java.sql.Connection;

public class DaoFactory {

    private static Connection connection = ConnectionPool.getConnection();
    private static DaoFactory daoFactory = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getFactory() {
        return daoFactory;
    }

    public ScriptExecutor getScriptExecutor() {
        return new ScriptExecutor(connection);
    }

    public void close() {
        ConnectionPool.releaseConnection(connection);
    }

    public MessageDao getMessageDao() {
        return new MessageDao(connection);
    }

    public KeyboardMarkUpDao getKeyboardMarkUpDao() {
        return new KeyboardMarkUpDao(connection);
    }

    public ButtonDao getButtonDao() {
        return new ButtonDao(connection);
    }

    public ResultDao getResultDao() {
        return new ResultDao(connection);
    }
}
