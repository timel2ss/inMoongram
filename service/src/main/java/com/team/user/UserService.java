package com.team.user;

import com.team.user.dto.FollowListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public FollowListDto.Response getFollowList(FollowListDto.Request requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        List<Follow> followees = user.getFollowees();

        List<FollowListDto.Response.UserInfo> userInfos = followees.stream()
                .map(Follow::getFollower)
                .map(followUser -> new FollowListDto.Response.UserInfo(followUser.getName(), followUser.getNickname()))
                .collect(Collectors.toCollection(LinkedList::new));

        return new FollowListDto.Response(userInfos, null);
    }
}
