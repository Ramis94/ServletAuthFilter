package ru.innopolis.university.ramis.dao;

import ru.innopolis.university.ramis.dao.impl.UserDAOImpl;

/**
 * Created by innopolis on 27.10.16.
 */
public class Factory {

    private static Factory instance = new Factory();
    private static UserDAO userDAO = new UserDAOImpl();

    public static synchronized Factory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO(){
        return userDAO;
    }
}
