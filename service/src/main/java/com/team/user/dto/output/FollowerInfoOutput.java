package com.team.user.dto.output;

import com.team.user.Follow;
import com.team.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerInfoOutput {
    private Long userId;
    private String nickName;
    private String name;
    private String profileImage;
    private boolean followBack;

    @Builder
    public FollowerInfoOutput(Long userId, String nickName, String name, String profileImage, boolean followBack) {
        this.userId = userId;
        this.nickName = nickName;
        this.name = name;
        this.profileImage = profileImage;
        this.followBack = followBack;
    }

    public FollowerInfoOutput(Follow follow, boolean followBack) {
        User follower = follow.getFollower();
        this.userId = follower.getId();
        this.nickName = follower.getNickname();
        this.name = follower.getName();
        this.profileImage = follower.getProfileImage();
        this.followBack = followBack;
    }
}
