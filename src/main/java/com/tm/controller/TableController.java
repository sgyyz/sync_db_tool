package com.tm.controller;

import com.tm.builder.TableRelationshipBuilder;
import com.tm.domain.Column;
import com.tm.domain.Condition;
import com.tm.domain.Identifier;
import com.tm.service.InsertDataService;
import com.tm.service.QueryDataService;
import com.tm.util.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by justin.li on 12/5/16.
 */

@RestController
@EnableAutoConfiguration
public class TableController {

    public static final String TRUE = "1=1";
    public static final int COUNT_PER_PAGE = 10000;

    @Autowired
    private QueryDataService queryDataService;

    @Autowired
    private InsertDataService insertDataService;

    @RequestMapping(method = POST, value = "/tables")
    public ResponseEntity<?> saveDataToLocal(@RequestBody Identifier identifier) throws Exception {
        TableRelationshipBuilder tableRelationshipBuilder = new TableRelationshipBuilder();
        initIdentifierMapper(identifier, tableRelationshipBuilder);
        initTableRelationshipMapper(identifier.getNames(), tableRelationshipBuilder);
        boolean flag = insertData(tableRelationshipBuilder);
        return new ResponseEntity<>(flag, flag ? CREATED : NOT_ACCEPTABLE);
    }

    private void initTableRelationshipMapper(List<String> tableNames, TableRelationshipBuilder tableRelationshipBuilder) throws SQLException {
        for (String tableName : tableNames) {
            tableRelationshipBuilder.initMap(tableName, 0);
        }
    }

    private boolean insertData(TableRelationshipBuilder tableRelationshipBuilder) throws Exception {
        boolean flag = true;
        for (Map.Entry<String, Integer> mapping
                : tableRelationshipBuilder.getSortedTableDeepMap().entrySet()) {
            String tableName = mapping.getKey();
            String identifier = tableRelationshipBuilder.getIdentifierMapper().get(tableName);
            for (int i = 0; i < getPageCount(tableName, identifier); i++) {
                ResultSet resultSet = queryDataService.queryData(tableName, identifier, i * COUNT_PER_PAGE, COUNT_PER_PAGE);
                flag &= insertDataService.insertData(resultSet, tableName, identifier);
                DBUtil.release(resultSet);
            }

        }
        return flag;
    }

    private int getPageCount(String tableName, String identifier) throws SQLException {
        return (identifier == null) ? DBUtil.getPageCount(DBUtil.getTotalRowCount(tableName, TRUE), COUNT_PER_PAGE)
                        : DBUtil.getPageCount(DBUtil.getTotalRowCount(tableName, identifier), COUNT_PER_PAGE);
    }

    private void initIdentifierMapper(Identifier identifier, TableRelationshipBuilder tableRelationshipBuilder) {
        if (identifier != null) {
            Condition condition = identifier.getCondition();
            String tableName = condition.getTableName();
            StringBuilder identifierContent = new StringBuilder("");
            for (Column column : condition.getColumns()) {

                identifierContent.append("`" + column.getName() + "`" + "=" + column.getValue().toString() + " and ");
            }
            tableRelationshipBuilder.initIdentifierMapper(tableName,
                    identifierContent.toString().substring(0, identifierContent.length() - 5));
        }
    }
}
