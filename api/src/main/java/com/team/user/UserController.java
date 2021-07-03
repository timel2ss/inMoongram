package com.team.user;

import com.team.user.dto.FollowListDto;
import com.team.user.dto.FollowListResponseDto;
import com.team.user.dto.command.FollowerInfoListCommand;
import com.team.user.dto.response.FollowerInfoListResponse;
import com.team.user.dto.UserProfileModificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
  
    @GetMapping("/{user_id}/following")
      public ResponseEntity<FollowListResponseDto> getFollowList(@PathVariable("user_id") Long userId) {
          return ResponseEntity.ok(
                  new FollowListResponseDto(followService.getFollowList(userId)));
    }
  
    @PatchMapping("/api/user/{id}/profile")
    public ResponseEntity<Void>
        profileModification(@PathVariable("id") Long userId,@Valid @RequestBody UserProfileModificationRequest reqDto){
        userService.modifyUserProfile(userId, UserProfileModificationRequest.toServiceDto(reqDto));
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
  
    @GetMapping("follower/list")
    public ResponseEntity<FollowerInfoListResponse> getFollowerList(@RequestParam(name = "user-id") Long userId) {
        return ResponseEntity.ok(
                new FollowerInfoListResponse(
                        userService.getFollowerList(new FollowerInfoListCommand(userId))
                )
        );
    }
}
