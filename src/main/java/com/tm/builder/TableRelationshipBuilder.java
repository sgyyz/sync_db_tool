package com.tm.builder;


import com.tm.domain.FkInfo;
import com.tm.util.DBUtil;
import com.tm.util.SortUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableRelationshipBuilder {

    private TableInfoBuilder tableInfoBuilder = new TableInfoBuilder();

    public Map<String, Integer> tableDeepMap = new HashMap<>();
    public Map<String, String> identifierMapper = new HashMap<>();

    public Map<String, String> getIdentifierMapper() {
        return identifierMapper;
    }

    public Map<String, Integer> getSortedTableDeepMap() {

        return SortUtil.compareByValueDesc(tableDeepMap);
    }

    public void initMap(String tableName, Integer deep) throws SQLException {
        putDeep(tableName, deep);
        List<FkInfo> fkInfos = tableInfoBuilder.getTableInfo(tableName);
        deep++;
        if (fkInfos.size() > 0) {

            for (int i = 0; i < fkInfos.size(); i++) {
                initMap(fkInfos.get(i).getFkTableName(), deep);
            }
        } else {
            return;
        }
    }

    public void initIdentifierMapper(String tableName, String identifier) {
        identifierMapper.put(tableName, identifier);
        Connection connection;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            List<FkInfo> fkInfos = tableInfoBuilder.getTableInfo(tableName);
            if (fkInfos.size() < 0) {
                return;
            } else {
                for (FkInfo fkInfo : fkInfos) {
                    String sql = "select " + fkInfo.getFkTableColumn() + " from " + fkInfo.getTableName() +
                            " " + "where" + " " + identifier;
                    connection = DBUtil.getSourceConnection();
                    stmt = connection.createStatement();
                    resultSet = stmt.executeQuery(sql);
                    if (resultSet.next()) {
                        String fkTableColumn = fkInfo.getFkTableColumn();
                        initIdentifierMapper(fkInfo.getFkTableName(),
                                fkTableColumn + "=" + resultSet.getObject(fkTableColumn));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(resultSet, stmt);
        }


    }

    private void putDeep(String tableName, Integer deep) {
        if (!tableDeepMap.containsKey(tableName) || tableDeepMap.get(tableName) < deep) {
            tableDeepMap.put(tableName, deep);
        }
    }


}
