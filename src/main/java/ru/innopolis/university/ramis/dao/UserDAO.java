package ru.innopolis.university.ramis.dao;

import ru.innopolis.university.ramis.model.User;

import java.sql.SQLException;

/**
 * Created by innopolis on 27.10.16.
 * Интерфейс для работы с пользователем
 */
public interface UserDAO {

    /**
     * Добавление пользователя
     * @param user
     * @throws SQLException
     */
    void add(User user) throws SQLException;

    /**
     * получение пользователя по email
     * @param email
     * @return
     * @throws SQLException
     */
    User getUserByEmail(String email) throws SQLException;

    /**
     * Обновление пользователя
     * @param user
     * @param withPassword
     * @throws SQLException
     */
    void update(User user, boolean withPassword) throws SQLException;

    /**
     * узнаем существует ли пользователь по email и паролю
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    boolean getUserAuth(String email, String password) throws SQLException;

}
