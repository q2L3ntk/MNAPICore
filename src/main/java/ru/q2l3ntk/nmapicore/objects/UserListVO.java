package ru.q2l3ntk.nmapicore.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserListVO {
    private final List<UserVO> users;
}
