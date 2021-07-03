package com.team.user;

import com.team.user.dto.UserProfileModificationPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.team.user.dto.command.FollowerInfoListCommand;
import com.team.user.dto.result.FollowerInfoListResult;
import com.team.user.dto.result.FollowerInfoResult;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
    
    @Transactional
    public FollowerInfoListResult getFollowerList(FollowerInfoListCommand command) {
        User user = findUserById(command.getUserId());

        return new FollowerInfoListResult(
                user.getFollowers().stream()
                .map(it -> new FollowerInfoResult(
                        it,
                        it.getFollower().getFollowers().stream()
                                .anyMatch(f4f -> f4f.getFollower().getId().equals(command.getUserId()))
                        )
                )
                .collect(Collectors.toList())
        );
    }
    
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new RuntimeException("일치하는 아이디가 존재하지 않습니다.")
                );
    }
  
    @Transactional
    public User findById(Long id) {
          return userRepository.findById(id)
                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
      }

    @Transactional
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }
}
