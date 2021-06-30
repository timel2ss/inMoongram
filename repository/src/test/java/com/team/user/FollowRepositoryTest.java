package com.team.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FollowRepositoryTest {
    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

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
    void findByFollowerAndFollowee() {
        User saveUser1 = userRepository.save(user1);
        User saveUser2 = userRepository.save(user2);
        Follow saveFollow = followRepository.save(follow);
        Follow findOne = followRepository.findByFollower_IdAndFollowee_Id(saveUser1.getId(), saveUser2.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저와 팔로우가 되어 있지 않습니다."));
        assertThat(findOne).isEqualTo(follow);
        assertThat(findOne.getFollower()).isEqualTo(saveFollow.getFollower());
        assertThat(findOne.getFollowee()).isEqualTo(saveFollow.getFollowee());
    }
}