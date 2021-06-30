package com.team.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Optional<Follow> findByFollower_IdAndFollowee_Id(Long followerId, Long followeeId);
}
