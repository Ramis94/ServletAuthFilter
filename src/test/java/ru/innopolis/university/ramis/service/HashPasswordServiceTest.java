package ru.innopolis.university.ramis.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.service.impl.HashPasswordServiceImpl;

import java.security.NoSuchAlgorithmException;

/**
 * Created by innopolis on 30.10.16.
 */
public class HashPasswordServiceTest {

    private static Logger logger = LoggerFactory.getLogger(HashPasswordServiceTest.class);

    private HashPasswordService hashPasswordService;

    @Before
    public void before(){
        logger.info("Test start");
        this.hashPasswordService = new HashPasswordServiceImpl();
    }

    @Test
    public void testHashSHA1() throws NoSuchAlgorithmException {
        logger.info("testHashSHA1 start");
        Assert.assertEquals(hashPasswordService.getHash("1"), "356a192b7913b04c54574d18c28d46e6395428ab");
    }

    @After
    public void after(){
        logger.info("Test finish");
    }
}
