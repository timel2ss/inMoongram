package com.team.user;

import com.team.exception.IdNotFoundException;
import com.team.user.dto.input.UserInfoInput;
import com.team.user.dto.input.UserProfileModificationInput;
import com.team.user.dto.output.FollowListOutput;
import com.team.user.dto.output.FollowerInfoListOutput;
import com.team.user.dto.output.UserInfoOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserInfoOutput getUserInfo(UserInfoInput input) {
        User user = userRepository.findById(input.getUserId())
                .orElseThrow(IdNotFoundException::new);
        return new UserInfoOutput(user);
    }

    @Transactional
    public void modifyUserProfile(Long userId, UserProfileModificationInput payload) {
        User user = findUserById(userId);
        User modification = User.builder()
                .email(payload.getEmail())
                .nickname(payload.getNickname())
                .name(payload.getName())
                .phoneNumber(payload.getPhoneNumber())
                .introduction(payload.getIntroduction())
                .sex(payload.getSex())
                .website(payload.getWebsite())
                .profileImage(payload.getProfileImage())
                .build();
        user.modifyProfile(modification);
    }

    @Transactional(readOnly = true)
    public FollowerInfoListOutput getFollowerList(Long userId) {
        List<Follower> followers = userRepository.findFollowerUserById(userId);
        return new FollowerInfoListOutput(followers, userId);
    }

    @Transactional(readOnly = true)
    public FollowListOutput getFollowList(Long userId) {
        User user = userRepository.findFollowingUserById(userId).orElseThrow(
                IdNotFoundException::new
        );
        Set<Follow> followees = user.getFollowees();

        List<FollowListOutput.UserInfo> userInfos = followees.stream()
                .map(follow -> FollowListOutput.UserInfo.builder()
                        .userId(follow.getFollowee().getId())
                        .name(follow.getFollowee().getName())
                        .nickname(follow.getFollowee().getNickname())
                        .profileImage(follow.getFollowee().getProfileImage())
                        .followId(follow.getId())
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));

//        TODO: add hashtag info
        return new FollowListOutput(userInfos, null);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(IdNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<User> findUsersByIds(List<Long> userIds) {
        return userIds.stream()
                .map(this::findUserById)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }
}
