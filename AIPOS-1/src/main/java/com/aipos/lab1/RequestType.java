package com.aipos.lab1;

import java.util.Arrays;
import java.util.Optional;

public enum RequestType {
    GET("GET"),
    POST("POST"),
    OPTIONS("OPTIONS");

    private String name;

    RequestType(String name){
        this.name = name;
    }

}
