package ru.innopolis.university.ramis.service;

import java.security.NoSuchAlgorithmException;

/**
 * Created by innopolis on 30.10.16.
 */
public interface HashPasswordService {

    /**
     * метод для получения хеша
     * @param arg
     * @return
     * @throws NoSuchAlgorithmException
     */
    String getHash(String arg) throws NoSuchAlgorithmException;
}
