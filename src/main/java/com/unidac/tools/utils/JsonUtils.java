package com.unidac.tools.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ClassUtils;

@UtilityClass
public class JsonUtils {

    public boolean isString(Object obj){
        return ClassUtils.isAssignable(obj.getClass(),String.class);
    }

    public String checkIfTransformJson(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return JsonUtils.isString(obj) ? obj.toString() : gson.toJson(obj);
    }
}
