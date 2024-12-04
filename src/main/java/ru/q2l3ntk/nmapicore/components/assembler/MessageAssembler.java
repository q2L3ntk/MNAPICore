package ru.q2l3ntk.nmapicore.components.assembler;

import org.springframework.stereotype.Component;
import ru.q2l3ntk.nmapicore.models.Message;
import ru.q2l3ntk.nmapicore.objects.MessageVO;

@Component
public class MessageAssembler {
    public MessageVO toMessageVO(Message message) {
        return new MessageVO(
                message.getId(),
                message.getSender().getId(),
                message.getRecipient().getId(),
                message.getConversation().getId(),
                message.getBody(),
                message.getCreatedAt().toString()
        );
    }
}
