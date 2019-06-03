package com.mitrais.registration.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.ResourceBundle;

@Getter
@Setter
public class ApiCodeReference {
    public static final String SUCCESS = "SUCCESS";
    public static final String BAD_REQUEST = "BAD_REQUEST";

    public static String getMessage(String errorCode) {
        try {
            var bundle = ResourceBundle.getBundle("ErrorBundle");
            var errorInBundle = bundle.getString(errorCode);

            if (!StringUtils.isEmpty(errorInBundle)) {
                return errorInBundle;
            }
        } catch (Exception ex) {
//            log.error("Unable to locate resource.");
        }
        return errorCode;
    }

}
