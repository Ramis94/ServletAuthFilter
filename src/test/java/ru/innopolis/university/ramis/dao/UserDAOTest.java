package ru.innopolis.university.ramis.dao;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.model.User;
import ru.innopolis.university.ramis.service.HashPasswordServiceTest;

import java.sql.SQLException;

/**
 * Created by innopolis on 30.10.16.
 */
public class UserDAOTest {

    private static Logger logger = LoggerFactory.getLogger(HashPasswordServiceTest.class);

    private Mockery context;

    @Before
    public void before(){
        logger.info("Test start");
        this.context = new JUnit4Mockery();
    }

    @Test
    public void testGetUserByEmail() throws SQLException {
        logger.info("This is testGetUserByEmail");
        final UserDAO userDAO = context.mock(UserDAO.class);
        User user = new User();
        user.setId(1l);
        user.setEmail("Example@example.com");
        user.setLogin("Example");

        context.checking(new Expectations(){{
            oneOf(userDAO).getUserByEmail("Example@example.com");
            will(returnValue(user));
        }});

        Assert.assertEquals(user , userDAO.getUserByEmail("Example@example.com"));
    }

    @Test
    public void testGetUserAuth() throws SQLException {
        logger.info("This is testGetUserAuth");
        final UserDAO userDAO = context.mock(UserDAO.class);
        context.checking(new Expectations(){{
            oneOf(userDAO).getUserAuth("Example@example.com", "example");
            will(returnValue(true));
        }});

        Assert.assertEquals(true, userDAO.getUserAuth("Example@example.com", "example"));
    }

    public void after(){
        logger.info("Test finish");
    }
}
