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
    public FollowListDto getFollowList(Long userId) {
        User user = userService.findById(userId);
        List<Follow> followees = user.getFollowees();

        List<FollowListDto.UserInfo> userInfos = followees.stream()
                .map(follow -> FollowListDto.UserInfo.builder()
                        .userId(follow.getFollowee().getId())
                        .name(follow.getFollowee().getName())
                        .nickname(follow.getFollowee().getNickname())
                        .profileImage(follow.getFollowee().getProfileImage())
                        .followId(follow.getId())
                        .build())
                .collect(Collectors.toCollection(LinkedList::new));

//        TODO: add hashtag info
        return new FollowListDto(userInfos, null);
    }
}
