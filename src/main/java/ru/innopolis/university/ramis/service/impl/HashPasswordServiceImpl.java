package ru.innopolis.university.ramis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.university.ramis.service.HashPasswordService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by innopolis on 30.10.16.
 * Сервис хеширование пароля
 */
public class HashPasswordServiceImpl implements HashPasswordService {

    private static Logger logger = LoggerFactory.getLogger(HashPasswordServiceImpl.class);

    /**
     * Метод для хеширования пароля SHA1
     * @param arg пароль
     * @return хеш
     * @throws NoSuchAlgorithmException
     */
    public String getHash(String arg) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        byte [] result = messageDigest.digest(arg.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            stringBuilder.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        logger.debug("Hash " + arg + " is " + stringBuilder.toString());
        return stringBuilder.toString();
    }
}
