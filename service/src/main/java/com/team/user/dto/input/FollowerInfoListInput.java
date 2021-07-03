package com.team.user.dto.input;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerInfoListInput {
    private Long userId;

    public FollowerInfoListInput(Long userId) {
        this.userId = userId;
    }
}
