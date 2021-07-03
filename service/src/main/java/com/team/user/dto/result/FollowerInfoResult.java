package com.team.user.dto.result;

import com.team.user.Follow;
import com.team.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerInfoResult {
    private Long userId;
    private String nickName;
    private String name;
    private String profileImage;
    private boolean followBack;

    @Builder
    public FollowerInfoResult(Long userId, String nickName, String name, String profileImage, boolean followBack) {
        this.userId = userId;
        this.nickName = nickName;
        this.name = name;
        this.profileImage = profileImage;
        this.followBack = followBack;
    }

    public FollowerInfoResult(Follow follow, boolean followBack) {
        User follower = follow.getFollower();
        this.userId = follower.getId();
        this.nickName = follower.getNickname();
        this.name = follower.getName();
        this.profileImage = follower.getProfileImage();
        this.followBack = followBack;
    }
}
