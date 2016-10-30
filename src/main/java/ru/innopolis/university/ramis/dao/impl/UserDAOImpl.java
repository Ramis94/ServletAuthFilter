package ru.innopolis.university.ramis.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.controllers.RegController;
import ru.innopolis.university.ramis.dao.UserDAO;
import ru.innopolis.university.ramis.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by innopolis on 27.10.16.
 * Класс для работы с пользователем
 */
public class UserDAOImpl extends AbstractRepository implements UserDAO {

    private static Logger logger = LoggerFactory.getLogger(RegController.class);

    /**
     * Добавление пользователя в БД
     * @param user
     * @throws SQLException
     */
    @Override
    public void add(User user) throws SQLException {
        String sql = "INSERT INTO \"user\"(email, password, login) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getEmail());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.executeUpdate();
        logger.info("User " + user + " is add");
    }

    /**
     * Получение пользователя(model User) по email
     * @param email
     * @return User
     * @throws SQLException
     */
    @Override
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM \"user\" WHERE email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = new User();
        while (resultSet.next()){
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setLogin(resultSet.getString("login"));
        }
        logger.info("Get user by name " + email);
        return user;
    }

    /**
     * обновление данных пользователя
     * @param user
     * @param withPassword
     * @throws SQLException
     */
    @Override
    public void update(User user, boolean withPassword) throws SQLException {
        PreparedStatement preparedStatement;
        //проверка на существование изменения пароля
        if (withPassword){
            preparedStatement = connection.prepareStatement("Update \"user\" SET email = ?, login = ?, password = ? WHERE id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
            logger.debug("User " + user.toString() + " is updated");
        } else {
            preparedStatement = connection.prepareStatement("Update \"user\" SET email = ?, login = ? WHERE id = ?");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
            logger.debug("User " + user.toString() + " is updated");
        }
    }

    /**
     * метод нужен для авторизации. поиск пользователя по email и сравнение паролей
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean getUserAuth(String email, String password) throws SQLException {
        boolean result = false;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM \"user\" WHERE email = ?");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            result = password.equals(resultSet.getString("password"));
            logger.debug("Email = " + email + " password = " + password + " is " + result);
        }
        return result;
    }
}