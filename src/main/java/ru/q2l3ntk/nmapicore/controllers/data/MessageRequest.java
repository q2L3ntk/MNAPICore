package ru.q2l3ntk.nmapicore.controllers.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageRequest {
    private Long recipientId;
    private String message;
}
