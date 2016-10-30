package ru.innopolis.university.ramis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by innopolis on 28.10.16.
 * Выход из личного кабинета
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet{

    private static Logger logger = LoggerFactory.getLogger(LogoutController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("User " + req.getSession().getAttribute("email") + " is logout");
        req.getSession().invalidate();
        req.getRequestDispatcher("auth/login.jsp").forward(req, resp);
    }
}
