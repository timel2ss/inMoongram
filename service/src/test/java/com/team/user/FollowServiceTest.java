package com.team.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {
    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private FollowService followService;

    private User user1;
    private User user2;
    private Follow follow;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .name("testUser1")
                .nickname("testNickname1")
                .email("test1@test.com")
                .password("testPassword1")
                .build();
        user2 = User.builder()
                .name("testUser2")
                .nickname("testNickname2")
                .email("test2@test.com")
                .password("testPassword2")
                .build();
        follow = Follow.builder()
                .follower(user1)
                .followee(user2)
                .build();
    }

    @Test
    void 팔로우_취소() {
        given(followRepository.findByFollowerAndFollowee(any(), any())).willReturn(Optional.of(follow));
        int followerCount = user1.getFollowees().size();
        int followeeCount = user2.getFollowers().size();
        followService.unfollow(user1, user2);
        assertThat(user1.getFollowers().size()).isEqualTo(followerCount - 1);
        assertThat(user2.getFollowers().size()).isEqualTo(followeeCount - 1);
    }
}