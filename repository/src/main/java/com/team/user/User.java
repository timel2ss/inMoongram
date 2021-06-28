package com.team.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String introduction;

    private Sex sex;

    private String website;

    @Column(name = "profile_image")
    private String profileImage;

    // 사용자를 팔로우 하는 사람들
    @OneToMany(mappedBy = "follower")
    private List<Follow> followers = new ArrayList<>();

    // 사용자가 팔로우 하는 사람들
    @OneToMany(mappedBy = "followee")
    private List<Follow> followees = new ArrayList<>();

    @Builder
    public User(String email, String password, String nickname, String name,
                String phoneNumber, String introduction, Sex sex, String website, String profileImage) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.sex = sex;
        this.website = website;
        this.profileImage = profileImage;
    }
}

