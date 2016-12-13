package com.tm.service;

import com.tm.util.DBUtil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.tm.util.DBUtil.getSourceConnection;

/**
 * Created by justin.li on 12/8/16.
 */

@Service
public class QueryDataServiciImp implements QueryDataService {
    @Override
    public ResultSet queryData(String tableName, String identifier, int startLocation, int countPerPage) throws SQLException {
        Connection connection = getSourceConnection();
        String sql = DBUtil.getPageSQL(tableName, identifier, startLocation, countPerPage);
        PreparedStatement stmt = connection.prepareStatement(sql);
        return stmt.executeQuery(sql);
    }


}
