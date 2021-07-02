package com.team.user;

import com.team.user.dto.FollowListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final FollowService followService;

    @GetMapping("/{nickname}/following")
    public ResponseEntity<FollowListDto.Response> getFollowList(@PathVariable("nickname") FollowListDto.Request request) {
        return ResponseEntity.ok(followService.getFollowList(request));
    }
}
