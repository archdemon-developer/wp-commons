package com.wildpulse.commons.exceptions;

import com.wildpulse.commons.exceptions.enums.WPErrorCode;
import com.wildpulse.commons.models.errors.WPErrorDetails;
import com.wildpulse.commons.models.errors.WPErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WPExceptionHandler {

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public WPErrorResponse handleSecurityException(SecurityException securityException) {
        List<WPErrorDetails> errors = new ArrayList<>();

        WPErrorDetails errorDetails =
                new WPErrorDetails(WPErrorCode.WP_AUTH_ERROR, securityException.getMessage());

        errors.add(errorDetails);

        return new WPErrorResponse(errors);
    }
}
