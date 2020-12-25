package com.hcmut.kolb.Response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class SucessResponse {
    private final Map<String, Object> values = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getValues(){
        return values;
    }

    @JsonAnySetter
    public SucessResponse put(String key, Object value){
        values.put(key, value);
        return this;
    }

}