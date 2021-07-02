package com.team.user;

import com.team.user.dto.FollowListDto;
import com.team.user.dto.FollowListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final FollowService followService;

    @GetMapping("/{user_id}/following")
    public ResponseEntity<FollowListResponseDto> getFollowList(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(
                new FollowListResponseDto(followService.getFollowList(userId)));
    }
}
