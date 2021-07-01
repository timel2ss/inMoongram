package com.team.user;

import com.team.user.dto.command.FollowerInfoListCommand;
import com.team.user.dto.response.FollowerInfoListResponse;
import com.team.user.dto.response.FollowerInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("follower/list")
    public ResponseEntity<FollowerInfoListResponse> getFollowerList(@Valid @RequestParam(name = "user-id") Long userId) {
        return ResponseEntity.ok(
                new FollowerInfoListResponse(
                        userService.getFollowerList(new FollowerInfoListCommand(userId))
                )
        );
    }
}
