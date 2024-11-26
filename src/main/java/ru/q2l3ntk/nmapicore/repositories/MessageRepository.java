package ru.q2l3ntk.nmapicore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.q2l3ntk.nmapicore.models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByConversationId(Long conversationId);
}
