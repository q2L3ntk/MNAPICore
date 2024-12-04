package ru.q2l3ntk.nmapicore.components.assembler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.q2l3ntk.nmapicore.models.Conversation;
import ru.q2l3ntk.nmapicore.objects.ConversationListVO;
import ru.q2l3ntk.nmapicore.objects.ConversationVO;
import ru.q2l3ntk.nmapicore.objects.MessageVO;
import ru.q2l3ntk.nmapicore.services.impl.ConversationServiceImpl;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ConversationAssembler {
    private ConversationServiceImpl conversationService;
    private MessageAssembler messageAssembler;

    public ConversationVO toConversationVO(Conversation conversation, Long userId) {
        ArrayList<MessageVO> conversationMessages = new ArrayList<>();
        conversation.getMessages().forEach(message -> {conversationMessages.add(messageAssembler.toMessageVO(message));});

        return new ConversationVO(
                conversation.getId(),
                conversationService.nameSecondParty(conversation, userId),
                conversationMessages
        );
    }

    public ConversationListVO toConversationListVO(ArrayList<Conversation> conversations, Long userId) {
        ArrayList<ConversationVO> conversationVOList = conversations.stream().map(
                conversation -> toConversationVO(conversation, userId)).collect(Collectors.toCollection(ArrayList::new)
        );
        return new ConversationListVO(conversationVOList);
    }
}
