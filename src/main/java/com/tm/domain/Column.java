package com.tm.domain;

/**
 * Created by justin.li on 12/10/16.
 */
public class Column {
    String name;
    Object value;

    public Column() {
    }

    public Column(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
