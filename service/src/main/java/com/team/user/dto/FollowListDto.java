package com.team.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class FollowListDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Request {
        private Long userId;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Response {
        private List<UserInfo> users;
        private List<HashtagInfo> hashtags;

        @Getter
        @AllArgsConstructor
        public static class UserInfo {
            private String name;
            private String nickname;
        }

        @Getter
        @AllArgsConstructor
        public static class HashtagInfo {
            private String hashtag;
            private Long count; // Number of posts with hashtag
        }
    }
}
