package ru.innopolis.university.ramis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.dao.Factory;
import ru.innopolis.university.ramis.dao.UserDAO;
import ru.innopolis.university.ramis.model.User;
import ru.innopolis.university.ramis.service.HashPasswordService;
import ru.innopolis.university.ramis.service.impl.HashPasswordServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by innopolis on 27.10.16.
 * Регистрация пользователя
 */
@WebServlet("/reg")
public class RegController extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(RegController.class);

    private UserDAO userDAO = Factory.getInstance().getUserDAO();
    private HashPasswordService hashPassword = new HashPasswordServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Get page registration");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("auth/registration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("confirm_password");
        String email = req.getParameter("email");
        User user = new User();
        //проверка на совпадение паролей
        if(!password.equals(confirm_password)){
            logger.debug("password and confirm_password is false");
            req.setAttribute("errorMessage", "Пароли не совподают!");
            req.getRequestDispatcher("auth/registration.jsp").forward(req, resp);
        } else {
            logger.debug("password and confirm_password is true");
            user.setEmail(email);
            try {
                //хеширование пароля
                user.setPassword(hashPassword.getHash(password));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            user.setLogin(req.getParameter("login"));
            try {
                userDAO.add(user);
                logger.debug("User " + user.toString() + " is add");
            } catch (SQLException e) {
                if("23505".equals(e.getSQLState())){
                    logger.error("Email : " + email + " is dublicate");
                    req.setAttribute("errorMessage", "Этот email уже зарегистрирован!");
                    req.getRequestDispatcher("auth/registration.jsp").forward(req, resp);
                    return;
                } else {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect("/auth");
        }
    }
}
