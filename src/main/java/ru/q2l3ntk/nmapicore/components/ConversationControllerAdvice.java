package ru.q2l3ntk.nmapicore.components;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.q2l3ntk.nmapicore.constants.ErrorResponse;
import ru.q2l3ntk.nmapicore.exceptions.ConversationIdInvalidException;

@ControllerAdvice
public class ConversationControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> conversationIdInvalidException(ConversationIdInvalidException conversationIdInvalidException) {
        ErrorResponse res = new ErrorResponse("", conversationIdInvalidException.getMessage());
        return ResponseEntity.unprocessableEntity().body(res);
    }
}
