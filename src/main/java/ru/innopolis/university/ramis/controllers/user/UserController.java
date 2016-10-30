package ru.innopolis.university.ramis.controllers.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.dao.Factory;
import ru.innopolis.university.ramis.dao.UserDAO;
import ru.innopolis.university.ramis.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by innopolis on 28.10.16.
 * Личный кабинет пользователя
 */
@WebServlet("/user")
public class UserController extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserDAO userDAO = Factory.getInstance().getUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = userDAO.getUserByEmail(req.getSession().getAttribute("email").toString());
            req.getSession().setAttribute("user_id", user.getId());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("login", user.getLogin());
            logger.debug("User : " + user.toString() + " is auth");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("user_private/user.jsp").forward(req, resp);
    }
}
