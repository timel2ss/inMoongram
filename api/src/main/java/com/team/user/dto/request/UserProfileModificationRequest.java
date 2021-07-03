package com.team.user.dto.request;

import com.team.user.Sex;
import com.team.user.dto.input.UserProfileModificationInput;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileModificationRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String nickname;

    private String name;

    private String phoneNumber;

    private String introduction;

    private Sex sex;

    private String website;

    private String profileImage;

    @Builder
    public UserProfileModificationRequest(String email, String nickname, String name, String phoneNumber,
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

    public static UserProfileModificationInput toServiceDto(UserProfileModificationRequest reqDto) {
        return UserProfileModificationInput.builder()
                .email(reqDto.getEmail())
                .nickname(reqDto.getNickname())
                .name(reqDto.getName())
                .phoneNumber(reqDto.getPhoneNumber())
                .introduction(reqDto.getIntroduction())
                .sex(reqDto.getSex())
                .website(reqDto.getWebsite())
                .profileImage(reqDto.getProfileImage())
                .build();
    }
}
