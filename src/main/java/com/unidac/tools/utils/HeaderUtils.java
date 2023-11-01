package com.unidac.tools.utils;


import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;


@UtilityClass
public class HeaderUtils {
    public HttpHeaders getHeaders(ResponseCookie jwtCookie){
        var httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE,jwtCookie.toString());
        return httpHeaders;
    }
}