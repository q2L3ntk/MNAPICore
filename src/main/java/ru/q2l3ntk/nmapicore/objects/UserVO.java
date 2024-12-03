package ru.q2l3ntk.nmapicore.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserVO {
    private final Long id;
    private final String username;
    private final String phoneNumber;
    private final String status;
    private final String createdAt;
}
