package com.team.user;

import com.team.user.dbutil.DatabaseCleanup;
import com.team.user.dbutil.DatabaseInsert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FollowControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleanup dbCleanup;

    @Autowired
    private DatabaseInsert dbInsert;

    private User user1;
    private User user2;

    private Follow follow1;

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

        dbInsert.saveUser(user1);
        dbInsert.saveUser(user2);

        follow1 = dbInsert.saveFollow(user1, user2);
    }

    @AfterEach
    void tearDown() {
        dbCleanup.execute();
    }

    @Test
    void 팔로우_취소() {
        given()
            .port(port)
            .accept("application/json")
            .contentType("application/json")
        .when()
            .delete( "follow/" + follow1.getId() + "/unfollow")
        .then()
            .statusCode(200);
    }
}