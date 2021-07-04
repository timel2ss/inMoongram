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

    public FollowInfoResponse(Long followerId, Long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    public FollowInfoResponse(FollowInfoOutput output) {
        this.followeeId = output.getFolloweeId();
        this.followerId = output.getFollowerId();
    }
}
