package com.apirest.customer.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T>{

    private String msg;

    private Object object;

    private ArrayList<T> list;

    public ResponseDTO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseDTO setObject(Object o) {
        this.object = o;
        return this;
    }

    public ResponseDTO setList(ArrayList<T> listObject) {
        list = listObject;
        return this;
    }
}
