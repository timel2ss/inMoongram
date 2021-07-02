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
        private String nickname;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Response {
        private List<UserInfo> users;
        private List<HashtagInfo> hashtags;

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        public static class UserInfo {
            private String name;
            private String nickname;
            private Long followId;
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
}
