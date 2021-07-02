package com.team.user;

import com.team.dbutil.DatabaseCleanup;
import com.team.dbutil.UserData;
import com.team.user.dto.UserProfileModificationRequest;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerAcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    DatabaseCleanup dbCleanup;

    @Autowired
    UserData userData;

    Long testUserId;

    @BeforeEach
    void setup(){
        RestAssured.port = port;
        User testUser = userData.saveUser("정준수","test1","jungjunsu@naver.com");
        testUserId = testUser.getId();
    }

    @AfterEach
    void setupEach() {
        dbCleanup.execute();
    }

    @Test
    void 유저정보변경_정상() {
        var reqDto = UserProfileModificationRequest.builder()
                .email("test@test.com")
                .name("testuser")
                .nickname("testspring")
                .website("test.com")
                .introduction("this is test")
                .phoneNumber("010-1234-5678")
                .profileImage("image")
                .sex(Sex.MALE)
                .build();
        assertThat(testUserId).isEqualTo(1L);
        given().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(reqDto).
        when().
                patch("/api/user/{user_id}/profile", testUserId).
        then().
                statusCode(204);
    }

    @Test
    void 유저정보변경_잘못된요청데이터(){
        var reqDto = UserProfileModificationRequest.builder()
                .name("testuser")
                .nickname("testspring")
                .website("test.com")
                .introduction("this is test")
                .phoneNumber("010-1234-5678")
                .profileImage("image")
                .sex(Sex.MALE)
                .build();
        assertThat(testUserId).isEqualTo(1L);
        given().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(reqDto).
                when().
                patch("/api/user/{user_id}/profile", testUserId).
                then().
                statusCode(400);
    }
}
