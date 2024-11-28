package ru.q2l3ntk.nmapicore.components;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.q2l3ntk.nmapicore.constants.ErrorResponse;
import ru.q2l3ntk.nmapicore.constants.ResponseConstants;
import ru.q2l3ntk.nmapicore.exceptions.MessageEmptyException;
import ru.q2l3ntk.nmapicore.exceptions.MessageRecipientInvalidException;

@ControllerAdvice
public class MessageControllerAdvice {
    @ExceptionHandler(MessageEmptyException.class)
    public ResponseEntity<ErrorResponse> messageEmpty(MessageEmptyException messageEmptyException) {
        ErrorResponse res = new ErrorResponse(ResponseConstants.MESSAGE_RECIPIENT_INVALID.getValue(), messageEmptyException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }

    @ExceptionHandler(MessageRecipientInvalidException.class)
    public ResponseEntity<ErrorResponse> messageRecipientInvalidId(MessageRecipientInvalidException messageRecipientInvalidException) {
        ErrorResponse res = new ErrorResponse(ResponseConstants.MESSAGE_RECIPIENT_INVALID.getValue(), messageRecipientInvalidException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }
}
