package com.example.webbackend.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> { // Add a generic type parameter T
    @JsonProperty("responseHeader")
    private ResponseHeader responseHeader;

    @JsonProperty("data") // Rename this field to a more general name like "data"
    private T data; // Use the generic type T instead of Dto

    public BaseResponse(ResponseHeader responseHeader, T data) {
        this.responseHeader = responseHeader;
        this.data = data;
    }

    public BaseResponse() {
    }
}
