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
    private final UserService userService;

    public void unfollow(Long userId, Long targetId) {
        Follow follow = followRepository.findByFollower_IdAndFollowee_Id(userId, targetId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저와 팔로우가 되어 있지 않습니다."));
        User user = userService.findOne(userId);
        User target = userService.findOne(targetId);

        user.getFollowees().remove(follow);
        target.getFollowers().remove(follow);
    }

    @Transactional(readOnly = true)
    public FollowListDto.Response getFollowList(FollowListDto.Request requestDto) {
        User user = userService.findOne(requestDto.getUserId());
        List<Follow> followees = user.getFollowees();

        List<FollowListDto.Response.UserInfo> userInfos = followees.stream()
                .map(Follow::getFollowee)
                .map(followUser -> new FollowListDto.Response.UserInfo(followUser.getName(), followUser.getNickname()))
                .collect(Collectors.toCollection(LinkedList::new));

        return new FollowListDto.Response(userInfos, null);
    }
}
