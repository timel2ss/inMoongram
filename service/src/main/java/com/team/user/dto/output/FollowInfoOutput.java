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
    private Long followId;

    public FollowInfoOutput(Long followerId, Long followeeId, Long followId) {
        this.followId = followId;
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    public FollowInfoOutput(Follow follow) {
        this.followId = follow.getId();
        this.followerId = follow.getFollower().getId();
        this.followeeId = follow.getFollowee().getId();
    }
}
