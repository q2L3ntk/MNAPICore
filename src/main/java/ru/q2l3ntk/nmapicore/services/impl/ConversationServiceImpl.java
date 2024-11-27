package ru.q2l3ntk.nmapicore.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.q2l3ntk.nmapicore.exceptions.ConversationIdInvalidException;
import ru.q2l3ntk.nmapicore.models.Conversation;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.repositories.ConversationRepository;
import ru.q2l3ntk.nmapicore.services.ConversationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConversationServiceImpl implements ConversationService {
    private ConversationRepository repository;

    @Override
    public Conversation createConversation(User userA, User userB) {
        Conversation conversation = new Conversation();
        conversation.setSender(userA);
        conversation.setRecipient(userB);
        repository.save(conversation);
        return conversation;
    }

    @Override
    public Boolean conversationExists(User userA, User userB) {
        return repository.findBySenderIdAndRecipientId(userA.getId(), userB.getId()) != null;
    }

    @Override
    public Conversation getConversation(User userA, User userB) {
        if (repository.findBySenderIdAndRecipientId(userA.getId(), userB.getId()) != null) {
            return repository.findBySenderIdAndRecipientId(userA.getId(), userB.getId());
        } else return null;
    }

    @Override
    public Conversation retrieveThread(Long conversationId) throws ConversationIdInvalidException {
        Optional<Conversation> conversation = repository.findById(conversationId);

        if (conversation.isPresent()) {
            return conversation.get();
        }

        throw new ConversationIdInvalidException(String.format("Invalid conversation id %d", conversationId));
    }

    @Override
    public List<Conversation> listUserConversations(Long userId) {
        ArrayList<Conversation> conversationList = new ArrayList<>();
        conversationList.addAll(repository.findBySenderId(userId));
        conversationList.addAll(repository.findByRecipientId(userId));

        return conversationList;
    }

    @Override
    public String nameSecondParty(Conversation conversation, Long userId) {
        if (conversation.getSender().getId().equals(userId)) return conversation.getRecipient().getUsername();
        else return conversation.getSender().getUsername();
    }
}
