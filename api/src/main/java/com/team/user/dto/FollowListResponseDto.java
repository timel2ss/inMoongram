package com.team.user.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FollowListResponseDto {
    private List<UserInfoResponse> users;
    private List<HashtagInfoResponse> hashtags;

    public FollowListResponseDto(FollowListDto followListDto) {
        this.users = followListDto.getUsers()
                .stream()
                .map(UserInfoResponse::new)
                .collect(Collectors.toList());
//        TODO: hashtags
        this.hashtags = null;
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserInfoResponse {
        private Long userId;
        private String name;
        private String nickname;
        private String profileImage;
        private Long followId;

        public UserInfoResponse(FollowListDto.UserInfo userInfo) {
            this.userId = userInfo.getUserId();
            this.name = userInfo.getName();
            this.nickname = userInfo.getNickname();
            this.profileImage = userInfo.getProfileImage();
            this.followId = userInfo.getFollowId();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class HashtagInfoResponse {
        private String hashtag;
        private Long count; // Number of posts with hashtag
        private Long followId;
    }
}
