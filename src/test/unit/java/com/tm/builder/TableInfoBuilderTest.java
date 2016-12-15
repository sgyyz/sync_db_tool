package com.tm.builder;

import com.tm.domain.Column;
import com.tm.util.DBUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by justin.li on 12/8/16.
 */
public class TableInfoBuilderTest {

    private TableInfoBuilder tableInfoBuilder;

    @Before
    public void setUp() throws Exception {
        tableInfoBuilder = new TableInfoBuilder();

    }

    @Test
    public void test_getTableInfo() throws Exception {
//        assertThat(tableInfoBuilder.getTableInfo("ssp_order").size(), is(2));
//        assertThat(tableInfoBuilder.getTableInfo("ssp_order").get(0).getTableName(), is("ssp_order"));
//        assertThat(tableInfoBuilder.getTableInfo("ssp_order").get(0).getColumn(), is("atv_campaign_id"));
//        assertThat(tableInfoBuilder.getTableInfo("ssp_order").get(0).getFkTableName(), is("atv_campaign"));
//        assertThat(tableInfoBuilder.getTableInfo("ssp_order").get(0).getFkTableColumn(), is("id"));
    }

    @Test
    public void test_getPrimaryKeys() throws SQLException {
//        List<String> primaryKeys = new ArrayList<>();
//        primaryKeys.addAll(tableInfoBuilder.getPrimaryKeyColumnNames("inventory"));
//        assertThat(primaryKeys.size(), is(4));
    }

    @Test
    public void test_getColumns() throws Exception {

//        ResultSet resultSet = DBUtil.getResultSet("ssp", "id=99");
//        resultSet.next();
//        List<Column> columns = tableInfoBuilder.getColumns(resultSet);
//        assertThat(columns.size(), is(11));
//        assertThat(columns.get(0).getValue().toString(), is("99"));
    }

}