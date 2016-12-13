package com.tm.service;

import org.springframework.stereotype.Service;

import java.sql.ResultSet;

/**
 * Created by justin.li on 12/8/16.
 */

@Service
public interface InsertDataService {
    boolean insertData(ResultSet resultSet, String tableName, String identifier) throws Exception;
}
