package com.tm.service;

import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by justin.li on 12/8/16.
 */

@Service
public interface QueryDataService {
    ResultSet queryData(String tableName, String identifier, int start, int countPerPage) throws SQLException;
}
