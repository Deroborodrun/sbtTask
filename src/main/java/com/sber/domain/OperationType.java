package com.sber.domain;

import java.util.HashMap;
import java.util.Map;

public enum OperationType {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLYING("*"),
    DIVISION("/");
    private String name;

    OperationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    static final Map<String, OperationType> map = new HashMap<>();

    static {
        for (OperationType type : OperationType.values()) {
            map.put(type.getName(), type);
        }
    }

    public static OperationType findByName(String name) {
        return map.get(name);
    }
}
