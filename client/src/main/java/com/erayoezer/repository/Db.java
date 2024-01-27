package com.erayoezer.repository;

public interface Db {
    void writeToDb(String key, String value);

    String readFromDb(String key);
}
