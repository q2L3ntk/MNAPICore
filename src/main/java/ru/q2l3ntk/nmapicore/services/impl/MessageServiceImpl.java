package ru.q2l3ntk.nmapicore.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.q2l3ntk.nmapicore.exceptions.MessageEmptyException;
import ru.q2l3ntk.nmapicore.exceptions.MessageRecipientInvalidException;
import ru.q2l3ntk.nmapicore.models.Conversation;
import ru.q2l3ntk.nmapicore.models.Message;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.repositories.ConversationRepository;
import ru.q2l3ntk.nmapicore.repositories.MessageRepository;
import ru.q2l3ntk.nmapicore.repositories.UserRepository;
import ru.q2l3ntk.nmapicore.services.ConversationService;
import ru.q2l3ntk.nmapicore.services.MessageService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository repository;
    private ConversationRepository conversationRepository;
    private ConversationService conversationService;
    private UserRepository userRepository;

    @Override
    public Message sendMessage(User sender, Long recipientId, String messageText) throws MessageEmptyException, MessageRecipientInvalidException {
        Optional<User> optional = userRepository.findById(recipientId);

        if (optional.isPresent()) {
            User recipient = optional.get();

            if (!messageText.isEmpty()) {
                Conversation conversation = (conversationService.conversationExists(sender, recipient)) ? conversationService.createConversation(sender, recipient) : conversationService.getConversation(sender, recipient);
                conversationRepository.save(conversation);

                Message message = new Message();
                message.setSender(sender);
                message.setRecipient(recipient);
                message.setBody(messageText);
                message.setConversation(conversation);
                repository.save(message);
                return message;
            }
        } else throw new MessageEmptyException(String.format("The recipient id %s is invalid", recipientId));

        throw new MessageEmptyException();
    }
}
