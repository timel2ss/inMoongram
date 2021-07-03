package com.team.user.dto.output;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FollowListOutput {
    private List<UserInfo> users;
    private List<HashtagInfo> hashtags;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserInfo {
        private Long userId;
        private String name;
        private String nickname;
        private String profileImage;
        private Long followId;

        @Builder
        public UserInfo(Long userId, String name, String nickname, String profileImage, Long followId) {
            this.userId = userId;
            this.name = name;
            this.nickname = nickname;
            this.profileImage = profileImage;
            this.followId = followId;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class HashtagInfo {
        private String hashtag;
        private Long count; // Number of posts with hashtag
        private Long followId;
    }
}
