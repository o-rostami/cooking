package com.infosys.coocking.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SearchOperation {

    EQUAL("eq"),
    NOT_EQUAL("neq"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL_TO("gte"),
    LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL_TO("lte"),
    IN("in"),
    NOT_IN("nin"),
    LIKE("like");

    private String value;

    SearchOperation(String value) {
        this.value = value;
    }

    public static SearchOperation fromValue(String value) {
        for (SearchOperation op : SearchOperation.values()) {

            if (String.valueOf(op.value).equalsIgnoreCase(value)) {
                return op;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }
}
