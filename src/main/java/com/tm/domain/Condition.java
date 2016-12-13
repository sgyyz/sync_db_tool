package com.tm.domain;

import java.util.List;

/**
 * Created by justin.li on 12/12/16.
 */
public class Condition {
    String tableName;
    List<Column> columns;

    public Condition() {
    }

    public Condition(String tableName, List<Column> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
