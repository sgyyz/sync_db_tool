package com.tm.domain;

/**
 * Created by justin.li on 12/7/16.
 */
public class PrimaryKey {
    String name;
    Object value;

    public PrimaryKey(String name, Object value) {
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
