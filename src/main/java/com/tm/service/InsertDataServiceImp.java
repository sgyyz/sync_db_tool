package com.tm.service;

import com.tm.builder.TableInfoBuilder;
import com.tm.domain.PrimaryKey;
import com.tm.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

import static com.tm.util.DBUtil.*;

/**
 * Created by justin.li on 12/8/16.
 */

@Service
public class InsertDataServiceImp implements InsertDataService {

    @Autowired
    private TableInfoBuilder tableInfoBuilder;

    @Override
    public boolean insertData(ResultSet rs, String tableName, String identifier) throws Exception {
        boolean flag;
        Connection connection = getTargetConnection();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        PreparedStatement prepareStatement = connection.prepareStatement(getInsertSQL(tableName));
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                List<String> primaryKeyColumnNames = tableInfoBuilder.getPrimaryKeyColumnNames(tableName);
                List<PrimaryKey> primaryKeys = tableInfoBuilder.getPrimaryKeys(rs, primaryKeyColumnNames);
                if (isExisted(tableName, primaryKeys)) {
                    DBUtil.update(primaryKeys, tableName, tableInfoBuilder.getColumns(rs));
                    break;
                }
                if (metaData.getColumnTypeName(i).equals("BIT") &&
                        metaData.getColumnClassName(i).equals("java.lang.Boolean")){

                   int value = (int) rs.getBytes(i)[0];
                    if (value == 48 || value == 0) {
                        value = 0;
                    } else {
                        value = 1;
                    }
                    prepareStatement.setObject(i, value);
                } else{
                    prepareStatement.setObject(i, rs.getObject(i));
                }

                if (i == columnCount) {
                    prepareStatement.addBatch();
                }
            }
        }
        flag = isSuccess(prepareStatement.executeBatch());
        prepareStatement.clearBatch();
        release(rs, prepareStatement);
        return flag;
    }




}
