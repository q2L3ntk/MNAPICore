package ru.q2l3ntk.nmapicore.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class ConversationVO {
    private final Long conversationId;
    private final String secondPartyUsername;
    private final ArrayList<MessageVO> messages;
}
