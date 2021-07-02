package com.team.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @DeleteMapping("/{follow_id}/unfollow")
    public ResponseEntity<Void> unfollow(@PathVariable("follow_id") Long followId) {
        followService.unfollow(followId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
