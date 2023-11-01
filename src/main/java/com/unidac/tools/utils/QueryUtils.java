package com.unidac.tools.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryUtils {
    public String toSnakeCase(String input) {
        if (input == null) return null;
        return input.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

}
