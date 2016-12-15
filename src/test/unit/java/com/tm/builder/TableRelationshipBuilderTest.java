package com.tm.builder;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TableRelationshipBuilderTest {

    private TableRelationshipBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new TableRelationshipBuilder();

    }

    @Test
    public void test_get_sorted_TableDeepMap() throws Exception {
//        builder.initMap("ssp_order_ranker", 0);
//        Map<String, Integer> sortedTableDeepMap = builder.getSortedTableDeepMap();
//        assertThat(sortedTableDeepMap.get("ssp_order_ranker"), is(0));
//        assertThat(sortedTableDeepMap.get("ssp_weekly_order"), is(1));
//        assertThat(sortedTableDeepMap.get("ssp_order"), is(2));
//        assertThat(sortedTableDeepMap.get("ssp"), is(3));
//        assertThat(sortedTableDeepMap.get("atv_campaign"), is(3));


    }

    @Test
    public void test_getIdentifierMapper() throws SQLException {
//        builder.initIdentifierMapper("ssp_order", "id=11111111111");
//        Assert.assertThat(builder.getIdentifierMapper().size(), is(1));
//        Assert.assertThat(builder.getIdentifierMapper().get("ssp_order"), is("id=11111111111"));
    }
}