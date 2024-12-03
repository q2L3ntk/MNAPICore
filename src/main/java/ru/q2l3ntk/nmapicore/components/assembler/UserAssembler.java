package ru.q2l3ntk.nmapicore.components.assembler;

import org.springframework.stereotype.Component;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.objects.UserListVO;
import ru.q2l3ntk.nmapicore.objects.UserVO;

import java.util.List;

@Component
public class UserAssembler {
    public UserVO toUserVO(User user) {
        return new UserVO(
                user.getId(),
                user.getUsername(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getCreatedAt().toString()
        );
    }

    public UserListVO toUserListVO(List<User> users) {
        List<UserVO> userVOList = users.stream().map(this::toUserVO).toList();
        return new UserListVO(userVOList);
    }
}
