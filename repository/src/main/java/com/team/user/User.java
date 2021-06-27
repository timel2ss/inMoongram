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

    @OneToMany(mappedBy = "follower")
    private List<Follow> Followings = new ArrayList<>();

    @OneToMany(mappedBy = "followee")
    private List<Follow> Followees = new ArrayList<>();

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

enum Sex{
    MALE,FEMALE,NONE
}
