package com.team.user.dto.input;

import com.team.user.Sex;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileModificationInput {

    private String email;

    private String nickname;

    private String name;

    private String phoneNumber;

    private String introduction;

    private Sex sex;

    private String website;

    private String profileImage;

    @Builder
    public UserProfileModificationInput(String email, String nickname, String name, String phoneNumber,
                                        String introduction, Sex sex, String website, String profileImage) {
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.sex = sex;
        this.website = website;
        this.profileImage = profileImage;
    }
}
