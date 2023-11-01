package com.unidac.tools.dto;

public record ResponseDTO(String message,Integer status,Object data,String timestamp,String path) {
}