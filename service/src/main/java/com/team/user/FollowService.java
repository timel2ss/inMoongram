package com.team.user;

import com.team.user.dto.FollowListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public void unfollow(User user, User target) {
        Follow follow = followRepository.findByFollowerAndFollowee(user, target)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저와 팔로우가 되어 있지 않습니다."));
        user.getFollowees().remove(follow);
        target.getFollowers().remove(follow);
    }

    @Transactional(readOnly = true)
    public FollowListDto.Response getFollowList(FollowListDto.Request requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        List<Follow> followees = user.getFollowees();

        List<FollowListDto.Response.UserInfo> userInfos = followees.stream()
                .map(Follow::getFollowee)
                .map(followUser -> new FollowListDto.Response.UserInfo(followUser.getName(), followUser.getNickname()))
                .collect(Collectors.toCollection(LinkedList::new));

        return new FollowListDto.Response(userInfos, null);
    }
}
