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

    public void unfollow(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저와 팔로우가 되어 있지 않습니다."));
        User user = userService.findByNickname(follow.getFollower().getNickname());
        User target = userService.findByNickname(follow.getFollowee().getNickname());

        user.getFollowees().remove(follow);
        target.getFollowers().remove(follow);
        followRepository.deleteById(follow.getId());
    }

    @Transactional(readOnly = true)
    public FollowListDto.Response getFollowList(FollowListDto.Request requestDto) {
        User user = userService.findByNickname(requestDto.getNickname());
        List<Follow> followees = user.getFollowees();

        List<FollowListDto.Response.UserInfo> userInfos = followees.stream()
                .map(follow -> new FollowListDto.Response.UserInfo(follow.getFollowee().getName(), follow.getFollowee().getNickname(), follow.getId()))
                .collect(Collectors.toCollection(LinkedList::new));

//        TODO: add hashtag info
        return new FollowListDto.Response(userInfos, null);
    }
}
