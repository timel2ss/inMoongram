package com.team.user;

import com.team.user.dto.FollowListDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private User user2;
    private User user3;

    private Follow follow1;
    private Follow follow2;

    @BeforeEach
    void setUp() {
        user = User.builder()
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
                .follower(user)
                .followee(user2)
                .build();

        follow2 = Follow.builder()
                .follower(user)
                .followee(user3)
                .build();
    }

    @Test
    void 팔로우_목록_조회() {
        given(userRepository.findById(any())).willReturn(Optional.of(user));
        FollowListDto.Request requestDto = new FollowListDto.Request(1L);
        FollowListDto.Response followList = userService.getFollowList(requestDto);

        assertThat(followList.getUsers().size()).isEqualTo(2);
        assertThat(followList.getUsers().get(0).getName()).isEqualTo(user2.getName());
        assertThat(followList.getUsers().get(0).getNickname()).isEqualTo(user2.getNickname());
        assertThat(followList.getUsers().get(1).getName()).isEqualTo(user3.getName());
        assertThat(followList.getUsers().get(1).getNickname()).isEqualTo(user3.getNickname());
//        assertThat(followList.getHashtags().size()).isEqualTo(1);
    }

}