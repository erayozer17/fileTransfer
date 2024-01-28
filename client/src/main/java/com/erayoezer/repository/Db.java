package com.erayoezer.repository;

import java.util.Optional;

public interface Db {
    void writeToDb(String key, String value);

    Optional<String> readFromDb(String key);
}
