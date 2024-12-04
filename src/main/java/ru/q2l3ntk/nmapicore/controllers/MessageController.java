package ru.q2l3ntk.nmapicore.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.q2l3ntk.nmapicore.components.assembler.MessageAssembler;
import ru.q2l3ntk.nmapicore.controllers.data.MessageRequest;
import ru.q2l3ntk.nmapicore.models.Message;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.objects.MessageVO;
import ru.q2l3ntk.nmapicore.repositories.UserRepository;
import ru.q2l3ntk.nmapicore.services.impl.MessageServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {
    private MessageServiceImpl messageService;
    private UserRepository userRepository;
    private MessageAssembler messageAssembler;

    public ResponseEntity<MessageVO> create(MessageRequest messageDetails, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User sender = userRepository.findByUsername(principal.getName());
        Message message = messageService.sendMessage(
                sender,
                messageDetails.getRecipientId(),
                messageDetails.getMessage()
        );

        return ResponseEntity.ok(messageAssembler.toMessageVO(message));
    }
}
