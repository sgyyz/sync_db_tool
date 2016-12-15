package com.tm.builder;

import com.tm.controller.TableController;
import com.tm.domain.Column;
import com.tm.domain.FkInfo;
import com.tm.domain.PrimaryKey;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.tm.util.DBUtil.getSourceConnection;

/**
 * Created by justin.li on 12/8/16.
 */

@Component
public class TableInfoBuilder {
    private final static Logger LOGGER = Logger.getLogger(TableController.class);

    public List<String> getPrimaryKeyColumnNames(String tableName) throws SQLException {
        Connection connection = getSourceConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableName);
        List<String> result = new ArrayList<>();
        while (primaryKeys.next()) {
            String pkName = primaryKeys.getString("COLUMN_NAME");
            result.add(pkName);
        }
        return result;
    }

    public List<String> getColumns(String tableName) throws SQLException {
        Connection connection = getSourceConnection();
        String sql = "select * from " + tableName + " limit 0,1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<String> result = new LinkedList<>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            result.add(metaData.getColumnName(i));
        }
        return result;
    }

    public List<Column> getColumns(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        List<Column> result = new LinkedList<>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {

            String name = metaData.getColumnName(i);
            Object value = null;
            if (metaData.getColumnTypeName(i).equals("BIT") &&
                    metaData.getColumnClassName(i).equals("java.lang.Boolean")) {
                value = (int) rs.getBytes(name)[0];
                if ((int) value == 48 || (int) value == 0) {
                    value = 0;
                } else {
                    value = 1;
                }
                LOGGER.info(value + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            } else {
                value = rs.getObject(name);
            }

            result.add(new Column(name, value));
        }
        return result;
    }

    public List<FkInfo> getTableInfo(String tableName) throws SQLException {
        Connection connection = getSourceConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet foreignKeys = metaData.getImportedKeys(connection.getCatalog(), null, tableName);
        List<FkInfo> fkInfos = new LinkedList();
        while (foreignKeys.next()) {
            String fkTableName = foreignKeys.getString("FKTABLE_NAME");
            String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
            String pkTableName = foreignKeys.getString("PKTABLE_NAME");
            String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");
            FkInfo fkInfo = new FkInfo(pkTableName, pkColumnName, fkTableName, fkColumnName);
            fkInfos.add(fkInfo);
        }
        return fkInfos;
    }

    public List<PrimaryKey> getPrimaryKeys(ResultSet rs, List<String> primaryKeyColumnNames) throws SQLException {
        List<PrimaryKey> primaryKeys = new ArrayList<>();
        for (String primaryKeyColumnName : primaryKeyColumnNames) {
            PrimaryKey pk = new PrimaryKey(primaryKeyColumnName, rs.getObject(primaryKeyColumnName));
            primaryKeys.add(pk);
        }
        return primaryKeys;
    }

}
