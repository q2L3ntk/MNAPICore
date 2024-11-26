package ru.q2l3ntk.nmapicore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.q2l3ntk.nmapicore.models.Conversation;

import java.util.List;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {
    List<Conversation> findBySenderId(Long Id);
    List<Conversation> findByRecipientId(Long Id);
    Conversation findBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
