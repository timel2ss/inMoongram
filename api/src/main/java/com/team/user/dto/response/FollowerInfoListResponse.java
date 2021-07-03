package com.team.user.dto.response;

import com.team.user.dto.output.FollowerInfoListOutput;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowerInfoListResponse {
    private List<FollowerInfoResponse> followerInfoResponses;

    public FollowerInfoListResponse(List<FollowerInfoResponse> followerInfoResponses) {
        this.followerInfoResponses = followerInfoResponses;
    }

    public FollowerInfoListResponse(FollowerInfoListOutput followerInfoListOutput) {
        this.followerInfoResponses = followerInfoListOutput
                .getFollowerInfoOutputList().stream()
                .map(FollowerInfoResponse::new)
                .collect(Collectors.toList());
    }
}
