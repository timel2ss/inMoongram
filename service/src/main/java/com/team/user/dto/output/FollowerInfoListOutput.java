package com.team.user.dto.output;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerInfoListOutput {
    private List<FollowerInfoOutput> followerInfoOutputList;

    public FollowerInfoListOutput(List<FollowerInfoOutput> followerInfoOutputList) {
        this.followerInfoOutputList = followerInfoOutputList;
    }
}
