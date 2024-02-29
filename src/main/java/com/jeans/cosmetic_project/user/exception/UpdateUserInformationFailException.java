package com.jeans.cosmetic_project.user.exception;

import com.jeans.cosmetic_project.common.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class UpdateUserInformationFailException extends AbstractException {

    public UpdateUserInformationFailException(String message) {
        super(message);
    }

    public UpdateUserInformationFailException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatusCode() {
        return null;
    }
}
