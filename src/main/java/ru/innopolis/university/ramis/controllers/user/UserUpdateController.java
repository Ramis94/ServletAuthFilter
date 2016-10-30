package ru.innopolis.university.ramis.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.dao.Factory;
import ru.innopolis.university.ramis.dao.UserDAO;
import ru.innopolis.university.ramis.model.User;
import ru.innopolis.university.ramis.service.HashPasswordService;
import ru.innopolis.university.ramis.service.impl.HashPasswordServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by innopolis on 29.10.16.
 * Обновление данных пользователя
 */
@WebServlet("/user/update")
public class UserUpdateController extends HttpServlet{

    private static Logger logger = LoggerFactory.getLogger(UserUpdateController.class);

    private UserDAO userDAO = Factory.getInstance().getUserDAO();
    private HashPasswordService hashPassword = new HashPasswordServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //получение измененных данных пользователя
        Long user_id = (Long) req.getSession().getAttribute("user_id");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        logger.debug("Request params : " + " | " + email + " | " + login + " | " + password + " | " + confirm_password);
        User user = new User();
        user.setId(user_id);
        user.setEmail(email);
        user.setLogin(login);
        boolean withPassword = false;
        //проверка на существование пароля
        if (!Objects.equals(password, "") || !Objects.equals(confirm_password, "")){
            logger.debug("Password OR Confirm_password is not null");
            if (password.equals(confirm_password)){
                logger.debug("Password and Confirm_password is true");
                try {
                    user.setPassword(hashPassword.getHash(password));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                withPassword = true;
            } else {
                logger.debug("Password and Confirm_password is false");
                req.getSession().setAttribute("message", "Пароли не совподают");
                resp.sendRedirect("/user");
                return;
                //req.getRequestDispatcher("/user_private/user.jsp").forward(req, resp);
            }
        }
        try {
            //обновление
            logger.debug("Update user, using password is " + withPassword);
            userDAO.update(user, withPassword);
        } catch (SQLException e) {
            req.getSession().setAttribute("message", "Не правильные данные");
            resp.sendRedirect("/user");
            //req.getRequestDispatcher("/user_private/user.jsp").forward(req, resp);
            return;
        }
        logger.info("User : " + user.toString() + " is updated");
        req.getSession().setAttribute("message", "Данные обновлены");
        resp.sendRedirect("/user");
        //req.getRequestDispatcher("/user_private/user.jsp").forward(req, resp);
    }
}