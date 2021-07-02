package com.team.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @EntityGraph(attributePaths = {"followers", "followees", "followers.follower.followers"})
    @Query("select u from User u where u.id = :id")
    Optional<User> findUserById(Long id);
}
