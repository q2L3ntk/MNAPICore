package ru.q2l3ntk.nmapicore.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.q2l3ntk.nmapicore.components.assembler.ConversationAssembler;
import ru.q2l3ntk.nmapicore.models.Conversation;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.objects.ConversationListVO;
import ru.q2l3ntk.nmapicore.objects.ConversationVO;
import ru.q2l3ntk.nmapicore.repositories.UserRepository;
import ru.q2l3ntk.nmapicore.services.impl.ConversationServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/conversations")
@AllArgsConstructor
public class ConversationController {
    private ConversationServiceImpl conversationService;
    private ConversationAssembler conversationAssembler;
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<ConversationListVO> list(HttpServletRequest request) {
        User user = userRepository.findByUsername(request.getUserPrincipal().getName());
        List<Conversation> conversations = conversationService.listUserConversations(user.getId()).stream().toList();

        return ResponseEntity.ok(conversationAssembler.toConversationListVO(conversations, user.getId()));
    }

    @GetMapping("/{conversation_id}")
    public ResponseEntity<ConversationVO> show(
            @PathVariable(name = "conversation_id") Long conversationId,
            HttpServletRequest request
    ) {
        User user = userRepository.findByUsername(request.getUserPrincipal().getName());
        var conversationThread = conversationService.retrieveThread(conversationId);

        return ResponseEntity.ok(conversationAssembler.toConversationVO(conversationThread, user.getId()));
    }
}
