package com.team.dbutil;

import com.team.user.Follow;
import com.team.user.FollowRepository;
import com.team.user.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FollowData {

    private final FollowRepository followRepository;

    public FollowData(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Transactional
    public Follow saveFollow(User follower, User followee) {
        Follow follow = createFollow(follower, followee);
        return followRepository.save(follow);
    }

    private Follow createFollow(User follower, User followee) {
        return Follow.builder()
                .followee(followee)
                .follower(follower)
                .build();
    }
}
