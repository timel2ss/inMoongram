package com.team.user.dto.response;

import com.team.user.dto.output.FollowerInfoOutput;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerInfoResponse {
    private Long userId;
    private String nickName;
    private String name;
    private String profileImage;
    private boolean followBack;

    public FollowerInfoResponse(FollowerInfoOutput result) {
        this.followBack = result.isFollowBack();
        this.name = result.getName();
        this.nickName = result.getNickName();
        this.profileImage = result.getProfileImage();
        this.userId = result.getUserId();
    }
}
