package com.team.user;

import com.team.user.dto.FollowListDto;
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

    @Mock
    private UserService userService;

    @InjectMocks
    private FollowService followService;

    private User user1;
    private User user2;
    private User user3;

    private Follow follow1;
    private Follow follow2;

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
        user3 = User.builder()
                .name("testUser3")
                .nickname("testNickname3")
                .email("test3@test.com")
                .password("testPassword3")
                .build();

        follow1 = Follow.builder()
                .follower(user1)
                .followee(user2)
                .build();
        follow2 = Follow.builder()
                .follower(user1)
                .followee(user3)
                .build();
    }

    @Test
    void 팔로우_취소() {
        given(followRepository.findByFollower_IdAndFollowee_Id(any(), any())).willReturn(Optional.of(follow1));
        given(userService.findOne(1L)).willReturn(user1);
        given(userService.findOne(2L)).willReturn(user2);

        int followerCount = user1.getFollowees().size();
        int followeeCount = user2.getFollowers().size();
        long followerId = 1L;
        long followeeId = 2L;

        followService.unfollow(followerId, followeeId);

        assertThat(user1.getFollowees().size()).isEqualTo(followerCount - 1);
        assertThat(user2.getFollowers().size()).isEqualTo(followeeCount - 1);
    }

    @Test
    void 팔로우_목록_조회() {
        given(userService.findOne(any())).willReturn(user1);
        FollowListDto.Request requestDto = new FollowListDto.Request(1L);
        FollowListDto.Response followList = followService.getFollowList(requestDto);

        assertThat(followList.getUsers().size()).isEqualTo(2);
        assertThat(followList.getUsers().get(0).getName()).isEqualTo(user2.getName());
        assertThat(followList.getUsers().get(0).getNickname()).isEqualTo(user2.getNickname());
        assertThat(followList.getUsers().get(1).getName()).isEqualTo(user3.getName());
        assertThat(followList.getUsers().get(1).getNickname()).isEqualTo(user3.getNickname());
//        assertThat(followList.getHashtags().size()).isEqualTo(1);
    }
}