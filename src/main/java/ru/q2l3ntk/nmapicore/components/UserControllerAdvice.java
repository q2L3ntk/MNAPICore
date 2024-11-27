package ru.q2l3ntk.nmapicore.components;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.q2l3ntk.nmapicore.constants.ErrorResponse;
import ru.q2l3ntk.nmapicore.constants.ResponseConstants;
import ru.q2l3ntk.nmapicore.exceptions.InvalidUserIdException;
import ru.q2l3ntk.nmapicore.exceptions.UserStatusEmptyException;
import ru.q2l3ntk.nmapicore.exceptions.UsernameUnavailableException;

@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UsernameUnavailableException.class)
    public ResponseEntity<ErrorResponse> usernameUnavailable(UsernameUnavailableException usernameUnavailableException) {
        ErrorResponse res = new ErrorResponse(ResponseConstants.USERNAME_UNAVAILABLE.getValue(), usernameUnavailableException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<ErrorResponse> invalidId(InvalidUserIdException invalidUserIdException) {
        ErrorResponse res = new ErrorResponse(ResponseConstants.INVALID_USER_ID.getValue(), invalidUserIdException.getMessage());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(UserStatusEmptyException.class)
    public ResponseEntity<ErrorResponse> statusEmpty(UserStatusEmptyException userStatusEmptyException) {
        ErrorResponse res = new ErrorResponse(ResponseConstants.EMPTY_STATUS.getValue(), userStatusEmptyException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }
}

