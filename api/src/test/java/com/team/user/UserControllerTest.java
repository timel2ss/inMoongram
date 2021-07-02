package com.team.user;

import com.team.user.dbutil.DatabaseCleanup;
import com.team.user.dbutil.DatabaseInsert;
import com.team.user.dto.FollowListDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleanup dbCleanup;

    @Autowired
    private DatabaseInsert dbInsert;

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

        dbInsert.saveUser(user1);
        dbInsert.saveUser(user2);
        dbInsert.saveUser(user3);

        follow1 = dbInsert.saveFollow(user1, user2);
        follow2 = dbInsert.saveFollow(user1, user3);
    }

    @AfterEach
    void tearDown() {
        dbCleanup.execute();
    }

    @Test
    void 팔로우_목록_조회() {
        FollowListDto.Request request = new FollowListDto.Request(user1.getNickname());

        FollowListDto.Response response =
                given()
                    .port(port)
                    .accept("application/json")
                    .contentType("application/json")
                    .body(request)
                .when()
                    .get(user1.getNickname() + "/following")
                .then()
                    .statusCode(200)
                    .extract()
                    .as(FollowListDto.Response.class);

        assertThat(response.getUsers().size()).isEqualTo(2);
        assertThat(response.getUsers().get(0).getName()).isEqualTo(user2.getName());
        assertThat(response.getUsers().get(0).getNickname()).isEqualTo(user2.getNickname());
        assertThat(response.getUsers().get(0).getFollowId()).isEqualTo(follow1.getId());
        assertThat(response.getUsers().get(1).getName()).isEqualTo(user3.getName());
        assertThat(response.getUsers().get(1).getNickname()).isEqualTo(user3.getNickname());
        assertThat(response.getUsers().get(1).getFollowId()).isEqualTo(follow2.getId());
    }
}