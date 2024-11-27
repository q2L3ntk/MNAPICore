package ru.q2l3ntk.nmapicore.services;

import ru.q2l3ntk.nmapicore.models.Message;
import ru.q2l3ntk.nmapicore.models.User;

public interface MessageService {
    Message sendMessage(User sender, Long recipientId, String messageText);
}
