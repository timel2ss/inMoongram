package com.team.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {
    private final FollowRepository followRepository;

    public void unfollow(User user, User target) {
        Follow follow = followRepository.findByFollowerAndFollowee(user, target)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저와 팔로우가 되어 있지 않습니다."));
        user.getFollowees().remove(follow);
        target.getFollowers().remove(follow);
    }
}
