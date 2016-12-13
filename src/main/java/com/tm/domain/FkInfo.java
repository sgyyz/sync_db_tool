package com.tm.domain;

/**
 * Created by justin.li on 12/7/16.
 */
public class FkInfo {
    String fkTableName;
    String fkTableColumn;
    String tableName;
    String column;

    public FkInfo(String fkTableName, String fkTableColumn, String tableName, String column) {
        this.fkTableName = fkTableName;
        this.fkTableColumn = fkTableColumn;
        this.tableName = tableName;
        this.column = column;
    }

    public String getFkTableName() {
        return fkTableName;
    }

    public void setFkTableName(String fkTableName) {
        this.fkTableName = fkTableName;
    }

    public String getFkTableColumn() {
        return fkTableColumn;
    }

    public void setFkTableColumn(String fkTableColumn) {
        this.fkTableColumn = fkTableColumn;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
