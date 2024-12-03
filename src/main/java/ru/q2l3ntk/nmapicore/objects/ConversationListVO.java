package ru.q2l3ntk.nmapicore.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ConversationListVO {
    private final List<ConversationVO> conversations;
}
