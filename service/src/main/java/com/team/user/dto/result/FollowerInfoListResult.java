package com.team.user.dto.result;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerInfoListResult {
    private List<FollowerInfoResult> followerInfoResultList;

    public FollowerInfoListResult(List<FollowerInfoResult> followerInfoResultList) {
        this.followerInfoResultList = followerInfoResultList;
    }
}
