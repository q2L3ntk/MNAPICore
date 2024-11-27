package ru.q2l3ntk.nmapicore.services;

import ru.q2l3ntk.nmapicore.models.Conversation;
import ru.q2l3ntk.nmapicore.models.User;

import java.util.List;

public interface ConversationService {
    Conversation createConversation(User userA, User userB);
    Boolean conversationExists(User userA, User userB);
    Conversation getConversation(User userA, User userB);
    Conversation retrieveThread(Long conversationId);
    List<Conversation> listUserConversations(Long userId);
    String nameSecondParty(Conversation conversation, Long userId);
}
