package ru.q2l3ntk.nmapicore.components;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.q2l3ntk.nmapicore.constants.ErrorResponse;
import ru.q2l3ntk.nmapicore.constants.ResponseConstants;
import ru.q2l3ntk.nmapicore.exceptions.UserDeactivatedException;

@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(UserDeactivatedException.class)
    public ResponseEntity<ErrorResponse> userDeactivated(UserDeactivatedException userDeactivatedException) {
        ErrorResponse res = new ErrorResponse(ResponseConstants.ACCOUNT_DEACTIVATED.getValue(), userDeactivatedException.getMessage());
        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    }
}