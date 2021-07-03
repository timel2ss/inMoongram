package com.team.user.dto.command;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerInfoListCommand {
    private Long userId;

    public FollowerInfoListCommand(Long userId) {
        this.userId = userId;
    }
}
