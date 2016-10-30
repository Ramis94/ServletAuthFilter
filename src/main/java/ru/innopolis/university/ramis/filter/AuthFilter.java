package ru.innopolis.university.ramis.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.dao.impl.AbstractRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by innopolis on 28.10.16.
 * Фильтр
 */
@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    static {
        try {
            AbstractRepository.getConnection();
            logger.debug("Add connection");
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //проверка на авторизацию пользователя и урл
        if (httpServletRequest.getSession().getAttribute("email") != null) {
            logger.debug("User is auth");
            chain.doFilter(request, response);
        } else if (httpServletRequest.getRequestURI().endsWith("/reg") && httpServletRequest.getSession().getAttribute("email") == null){
            logger.debug("registration page");
            request.getRequestDispatcher("/reg").forward(request, response);
        }else {
            logger.debug("Auth page");
            request.getRequestDispatcher("/auth").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        logger.debug("Filter destroy");
    }
}
