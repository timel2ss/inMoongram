package com.team.user.dto.output;

import com.team.user.Follow;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowInfoOutput {
    private Long followerId;
    private Long followeeId;

    public FollowInfoOutput(Long followerId, Long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    public FollowInfoOutput(Follow follow) {
        this.followerId = follow.getFollower().getId();
        this.followeeId = follow.getFollowee().getId();
    }
}
