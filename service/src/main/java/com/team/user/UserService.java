package com.team.user;

import com.team.user.dto.command.FollowerInfoListCommand;
import com.team.user.dto.result.FollowerInfoListResult;
import com.team.user.dto.result.FollowerInfoResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public FollowerInfoListResult getFollowerList(FollowerInfoListCommand command) {
        User user = userRepository.findUserById(command.getUserId())
                .orElseThrow(() -> new RuntimeException("일치하는 아이디가 존재하지 않습니다"));

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
}
