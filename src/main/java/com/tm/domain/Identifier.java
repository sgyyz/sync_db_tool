package com.tm.domain;

import java.util.List;

/**
 * Created by justin.li on 12/7/16.
 */
public class Identifier {

    List<String> names;


    Condition condition;


    public Identifier() {
    }

    public Identifier(List<String> names, Condition condition) {
        this.names = names;
        this.condition = condition;
    }


    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
