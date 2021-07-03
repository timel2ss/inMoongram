package com.team.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByNickname(String nickname);

    @EntityGraph(attributePaths = {"followers", "followees", "followers.follower.followers","followees.followee"})
    @Query("select u from User u where u.id = :id")
    Optional<User> findUserById(@Param("id")Long id);
}
