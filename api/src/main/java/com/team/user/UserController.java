package com.team.user;

import com.team.user.dto.UserProfileModificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/api/user/{id}/profile")
    public ResponseEntity<Void>
        profileModification(@PathVariable("id") Long userId,@Valid @RequestBody UserProfileModificationRequest reqDto){
        userService.modifyUserProfile(userId, UserProfileModificationRequest.toServiceDto(reqDto));
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
