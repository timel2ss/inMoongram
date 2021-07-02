package com.team.user;

import com.team.user.dto.UserProfileModificationPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void modifyUserProfile(Long userId, UserProfileModificationPayload payload) {
        User user = findUserById(userId);
        user.modifyProfile(payload.getEmail(), payload.getNickname(), payload.getName(),
                payload.getPhoneNumber(), payload.getIntroduction(), payload.getSex(),
                payload.getWebsite(), payload.getProfileImage()
        );
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new RuntimeException("존재하지 않는 유저입니다.")
                );
    }
}
