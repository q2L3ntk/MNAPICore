package ru.q2l3ntk.nmapicore.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageVO {
    private final Long id;
    private final Long senderId;
    private final Long recipientId;
    private final Long conversationId;
    private final String body;
    private final String createdAt;
}
