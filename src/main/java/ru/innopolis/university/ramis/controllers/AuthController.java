package ru.innopolis.university.ramis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.dao.Factory;
import ru.innopolis.university.ramis.dao.UserDAO;
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

/**
 * Created by gramis on 28.10.16.
 * Класс для авторизации пользователя
 */
@WebServlet("/auth")
public class AuthController extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private UserDAO userDAO = Factory.getInstance().getUserDAO();
    private HashPasswordService hashPassword = new HashPasswordServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //получение email и пароля
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        logger.debug("Auth request params = " + email + " and " + password);

        try {
            //проверка на наличии в БД пользователя
            boolean isUserValid = userDAO.getUserAuth(email, hashPassword.getHash(password));
            if(isUserValid){
                logger.info("User " + email + " is auth");
                req.getSession().setAttribute("email", email);
                resp.sendRedirect("/user");
            } else {
                logger.debug("Invalid data : " + email + " " + password);
                req.setAttribute("errorMessage", "Ошибка! Попробуйте еще раз.");
                req.getRequestDispatcher("auth/login.jsp").forward(req, resp);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
