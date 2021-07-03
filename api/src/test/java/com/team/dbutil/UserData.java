package com.team.dbutil;

import com.team.user.FollowRepository;
import com.team.user.Sex;
import com.team.user.User;
import com.team.user.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserData {

    private final UserRepository userRepository;

    public UserData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(String name, String nickName, String email) {
        User user = createUser(name, nickName, email);
        return userRepository.save(user);
    }

    private User createUser(String name, String nickName, String email) {
        return User.builder()
                .sex(Sex.MALE)
                .phoneNumber("010-2222-2222")
                .profileImage("12")
                .password("1234")
                .nickname(nickName)
                .introduction("안녕하세요")
                .email(email)
                .name(name)
                .website("naver.com")
                .build();
    }
}
