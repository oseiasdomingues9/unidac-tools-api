package com.unidac.tools.utils;

import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class PaginationUtil {

    public <T> List<T> paginate(List<T> sourceList, int page, int pageSize) {
        int totalSize = sourceList.size();
        int startIndex = page * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalSize);
        if (startIndex >= totalSize) return Collections.emptyList();
        return sourceList.subList(startIndex, endIndex);
    }
}
