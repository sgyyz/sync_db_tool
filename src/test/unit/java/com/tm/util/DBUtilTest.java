package com.tm.util;


import com.tm.builder.TableRelationshipBuilder;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class DBUtilTest {


    @Test
    public void test_getResultSet() throws SQLException {
        TableRelationshipBuilder builder = new TableRelationshipBuilder();
        builder.initIdentifierMapper("ssp", "id=99");
        ResultSet rs = DBUtil.getResultSet("ssp", builder.getIdentifierMapper().get("ssp"));
        if (rs.next()) {
            assertThat(rs.getObject("id"), is("99"));
        }
        rs.close();
    }

    @Test
    public void test_getInsertSQL() throws Exception {
        assertThat(DBUtil.getInsertSQL("ssp"),
                is("insert into ssp (`id`,`version`,`created_date`,`created_by`,`modified_date`,`modified_by`,`ssp_id`," +
                        "`mnemonic`,`display_name`,`email`,`ftp_site`) values(?,?,?,?,?,?,?,?,?,?,?)"));
    }


    @Test
    public void test_getTotalRowCount() throws SQLException {
//        assertThat(DBUtil.getTotalRowCount("inventory", "day=2"), is(1));
    }

    @Test
    public void test_getPageCount(){
        assertThat(DBUtil.getPageCount(3, 10), is(1));
    }

    @Test
    public void test_getPageSQL(){
        assertThat(DBUtil.getPageSQL("ssp", "id=99",0, 3),
                is("select * from ssp where id=99 limit 0,3"));
    }

    @Test
    public void test_update() throws SQLException {
//        TableInfoBuilder tableInfoBuilder = new TableInfoBuilder();
//        ResultSet resultSet = DBUtil.getResultSet("ssp", "id=99");
//        resultSet.next();
//        assertThat(DBUtil.getUpdateSQL(tableInfoBuilder.getPrimaryKeys(resultSet,
//                tableInfoBuilder.getPrimaryKeyColumnNames("ssp")),"ssp", tableInfoBuilder.getColumns(resultSet)),
//                is("select * from ssp where id=99 limit 0,3"));
    }

}