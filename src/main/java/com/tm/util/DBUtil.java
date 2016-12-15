package com.tm.util;

import com.tm.builder.TableInfoBuilder;
import com.tm.domain.Column;
import com.tm.domain.PrimaryKey;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DBUtil {
    private static Properties properties = new Properties();
    private static Connection sourceConnection = null;
    private static Connection targetConnection = null;

    static {
        try {
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("application.properties"));
            Class.forName(properties.getProperty("spring.datasource.driver-class-name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getSourceConnection() throws SQLException {
        if (sourceConnection == null) {
            sourceConnection = DriverManager.getConnection(properties.getProperty("spring.datasource.url"),
                    properties.getProperty("spring.datasource.username"),
                    properties.getProperty("spring.datasource.password"));
        }
        return sourceConnection;
    }

    public static Connection getTargetConnection() throws SQLException {
        if (targetConnection == null) {
            targetConnection = DriverManager.getConnection(properties.getProperty("spring.datasource.secondary.url"),
                    properties.getProperty("spring.datasource.secondary.username"),
                    properties.getProperty("spring.datasource.secondary.password"));
        }
        return targetConnection;
    }


        public static ResultSet getResultSet(String tableName, String identifier) throws SQLException {
            Connection connection = getSourceConnection();
            String sql = "select * from " + tableName;
            if (identifier != null) {
                sql += " " + "where" + " " + identifier;
            }
            PreparedStatement stmt = connection.prepareStatement(sql);
            return stmt.executeQuery(sql);
        }



    public static String getInsertSQL(String tableName) throws Exception {
        Connection connection = getSourceConnection();
        TableInfoBuilder tableInfoBuilder = new TableInfoBuilder();
        List<String> columnNames = tableInfoBuilder.getColumns(tableName);
        String sql = "insert into" + " " + tableName + " (";
        for (String columnName : columnNames) {
            sql += "`" + columnName + "`" + ",";

        }
        sql = sql.substring(0, sql.length() - 1) + ")" + " values(";
//        String sql = "insert into" + " " + tableName + " " +
        ResultSet rs = connection.createStatement()
                .executeQuery("select * from " + tableName + " limit 0,1");
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            sql += "?,";
        }
        release(rs);
        return sql.substring(0, sql.length() - 1) + ")";
    }

    public static int getTotalRowCount(String tableName, String identifier) throws SQLException {
        String sql = "select count(*) from " + tableName + " where " + identifier;
        Connection sourceConnection = getSourceConnection();
        Statement statement = sourceConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        int totalRowCount = resultSet.getInt(1);
        DBUtil.release(resultSet, statement);
        return totalRowCount;
    }

    public static int getPageCount(int rowCount, int countPerPage) {

        return rowCount % countPerPage == 0 ? rowCount / countPerPage : (rowCount / countPerPage) + 1;
    }

    public static String getPageSQL(String tableName, String identifier, int startLocation, int countPerPage) {
        if (identifier == null) {
            return "select * from " + tableName + " limit " + startLocation + "," + countPerPage;
        }else{
            return "select * from " + tableName +" " + "where" + " "
                    + identifier + " limit " + startLocation + "," + countPerPage;
        }
    }

    public static boolean isSuccess(int[] results) {
        boolean flag = true;
        for (int i = 0; i < results.length; i++) {
            if (results[i] >= 0) {
                flag &= true;
            } else {
                flag &= false;
            }

        }
        return flag;
    }

    public static String getSelectSQL(String tableName, List<PrimaryKey> primaryKeys) {
        String sql = "select * from " + tableName + " where ";
        for (PrimaryKey primaryKey : primaryKeys) {
            Object value = primaryKey.getValue();
            if (value != null && !value.equals("")) {
                if (value instanceof String || value instanceof java.sql.Date ||
                        value instanceof java.sql.Time || value instanceof java.sql.Timestamp) {
                    sql = sql + "`" + primaryKey.getName() + "`" + "=" +  "'" + value +"'"+ " and ";

                }else{
                    sql = sql + "`" + primaryKey.getName() + "`" + "=" + value + " and ";
                }


            }

        }
        return sql.substring(0, sql.length() - 5);
    }

    public static boolean isExisted(String tableName, List<PrimaryKey> primaryKeys) throws SQLException {
        Statement stmtTarget = getTargetConnection().createStatement();
        ResultSet resultTarget = stmtTarget.executeQuery(getSelectSQL(tableName, primaryKeys));
        if (resultTarget.next()) {

            return true;
        }
        release(resultTarget, stmtTarget);

        return false;
    }

    public static void release(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void release(Statement stmt) {
        if (stmt != null)
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void release(ResultSet rs, Statement statement) {
        release(rs);
        release(statement);
    }

    public static String getUpdateSQL(List<PrimaryKey> primaryKeys, String tableName, List<Column> columns) throws SQLException {
        String sql = "update " + tableName + " set";
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            Object value = column.getValue();
            if (value instanceof String || value instanceof java.sql.Date ||
                    value instanceof java.sql.Time || value instanceof java.sql.Timestamp) {
                sql += " " + "`" + column.getName() + "`" + "=" + "'" + value +"'" +",";

            }else{
                sql += " " + "`" + column.getName() + "`"  + "=" + value +",";
            }

        }
        sql = sql.substring(0,sql.length() - 1) + " where ";
        for (PrimaryKey primaryKey : primaryKeys) {
            Object value = primaryKey.getValue();
            String name = primaryKey.getName();
            if (value instanceof String || value instanceof java.sql.Date ||
                    value instanceof java.sql.Time || value instanceof java.sql.Timestamp) {
                sql += "`" + name + "`" + "=" +"'" + value +"'"+ " and ";

            }else{
                sql += "`" + name + "`" + "=" + value + " and ";
            }


        }

        return sql.substring(0, sql.length() - 5);
    }

    public static void update(List<PrimaryKey> primaryKeys, String tableName, List<Column> columns) throws SQLException {
        Connection connection = DBUtil.getTargetConnection();
        String updateSQL = getUpdateSQL(primaryKeys, tableName, columns);
        Statement statement = connection.createStatement();
        statement.executeUpdate(updateSQL);
        release(statement);
    }

}
