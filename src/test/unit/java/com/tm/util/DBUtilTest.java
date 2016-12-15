package com.tm.util;


import com.tm.builder.TableInfoBuilder;
import com.tm.builder.TableRelationshipBuilder;
import com.tm.domain.PrimaryKey;
import org.junit.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class DBUtilTest {


    @Test
    public void test_getResultSet() throws SQLException {
//        TableRelationshipBuilder builder = new TableRelationshipBuilder();
//        builder.initIdentifierMapper("job_definition", "id=11");
//        ResultSet rs = DBUtil.getResultSet("job_definition", builder.getIdentifierMapper().get("ssp"));
//        if (rs.next()) {
//            assertThat(rs.getObject("id"), is("11"));
//            assertThat(rs.getString("data"), is("good"));
//            assertThat(rs.getBytes("singleton")[0], is((byte)0));
//        }
//        rs.close();
    }

    @Test
    public void test_getInsertSQL() throws Exception {
//        assertThat(DBUtil.getInsertSQL("ssp"),
//                is("insert into ssp (`id`,`version`,`created_date`,`created_by`,`modified_date`,`modified_by`,`ssp_id`," +
//                        "`mnemonic`,`display_name`,`email`,`ftp_site`) values(?,?,?,?,?,?,?,?,?,?,?)"));
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
//        assertThat(DBUtil.getPageSQL("ssp", "id=99",0, 3),
//                is("select * from ssp where id=99 limit 0,3"));
    }

    @Test
    public void test_update() throws SQLException {
//        TableInfoBuilder tableInfoBuilder = new TableInfoBuilder();
//        ResultSet resultSet = DBUtil.getResultSet("archive_inventory", "1=1");
//        resultSet.next();
//        assertThat(DBUtil.getUpdateSQL(tableInfoBuilder.getPrimaryKeys(resultSet,
//                tableInfoBuilder.getPrimaryKeyColumnNames("archive_inventory")),"archive_inventory", tableInfoBuilder.getColumns(resultSet)),
//                is("select * from ssp where id=99 limit 0,3"));
    }

    @Test
    public void test_selectSQL() throws SQLException {
//        TableInfoBuilder tableInfoBuilder = new TableInfoBuilder();
//        ResultSet resultSet = DBUtil.getResultSet("archive_inventory", "day=33 and update_date='2016-12-11 11:11:12'");
//        resultSet.next();
////        assertThat(resultSet.getString("update_date"), is(""));
//        List<PrimaryKey> primaryKeys = tableInfoBuilder.getPrimaryKeys(resultSet,
//                tableInfoBuilder.getPrimaryKeyColumnNames("archive_inventory"));
//        assertThat(DBUtil.getSelectSQL("archive_inventory", primaryKeys), is("....."));
    }

}