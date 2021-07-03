package com.team.user;

import com.team.exception.IdNotFoundException;
import com.team.user.dto.output.FollowListOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;

    public void unfollow(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new IdNotFoundException("해당 유저와 팔로우가 되어 있지 않습니다."));
        User user = userService.findByNickname(follow.getFollower().getNickname());
        User target = userService.findByNickname(follow.getFollowee().getNickname());

        user.getFollowees().remove(follow);
        target.getFollowers().remove(follow);
        followRepository.deleteById(follow.getId());
    }
}
