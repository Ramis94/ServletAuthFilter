package ru.innopolis.university.ramis.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.controllers.RegController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by innopolis on 27.10.16.
 * подключение к БД
 */
public abstract class AbstractRepository {

    //добавить схему
    //прописать настройки в файл
    private static Logger logger = LoggerFactory.getLogger(RegController.class);

    static Connection connection;

    //pool соединение
    public static void getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.postgresql.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/products", "postgres", "postgres");
        logger.debug("Connection is add");
    }

    public static void closeConnection() throws SQLException {
        connection.close();
        logger.debug("connection is close");
    }
}
