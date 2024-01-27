package com.erayoezer.repository;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryDbImpl implements Db {
    private static ConcurrentHashMap<String, String> DB;

    @Override
    public void writeToDb(String key, String value) {
        getDb().put(key, value);
    }

    @Override
    public String readFromDb(String key) {
        return getDb().get(key);
    }

    private ConcurrentHashMap<String, String> getDb() {
        if (DB == null) {
            DB = new ConcurrentHashMap<>();
        }
        return DB;
    }
}
