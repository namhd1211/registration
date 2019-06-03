package com.mitrais.registration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApiResponse {
    private String code;
    private String message;
    private Object data;

    public ApiResponse(String code, Object data) {
        this.code = code;
        this.message = ApiCodeReference.getMessage(code);
        this.data = data;
    }
}
