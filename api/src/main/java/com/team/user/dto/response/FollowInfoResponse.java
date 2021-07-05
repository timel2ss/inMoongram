package com.team.user.dto.response;

import com.team.user.dto.output.FollowInfoOutput;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowInfoResponse {
    private Long followerId;
    private Long followeeId;
    private Long followId;

    public FollowInfoResponse(Long followerId, Long followeeId, Long followId) {
        this.followId = followId;
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    public FollowInfoResponse(FollowInfoOutput output) {
        this.followId = output.getFollowId();
        this.followeeId = output.getFolloweeId();
        this.followerId = output.getFollowerId();
    }
}
