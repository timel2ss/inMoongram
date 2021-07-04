package com.team.user;

import com.team.dbutil.DatabaseCleanup;
import com.team.dbutil.FollowData;
import com.team.dbutil.UserData;
import com.team.user.dto.request.FollowRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FollowAcceptanceTest {
    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleanup dbCleanup;

    @Autowired
    private UserData userData;

    @Autowired
    private FollowData followData;

    private User user1;
    private User user2;

    private Follow follow1;

    @BeforeEach
    void setUp() {
        user1 = userData.saveUser("testUser1", "testNickname1", "test1@test.com");
        user2 = userData.saveUser("testUser2", "testNickname2", "test2@test.com");
        follow1 = followData.saveFollow(user1, user2);
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
                .delete("follow/" + follow1.getId() + "/unfollow")
        .then()
                .statusCode(204);
    }

    @Test
    void 팔로우() {
        given()
                .port(port)
                .accept("application/json")
                .contentType("application/json")
                .body(new FollowRequest(user1.getId(), user2.getId()))
        .when()
                .post("/follow")
        .then()
                .statusCode(201)
                .body("followerId", is(user1.getId().intValue()))
                .body("followeeId", is(user2.getId().intValue()));
    }
}