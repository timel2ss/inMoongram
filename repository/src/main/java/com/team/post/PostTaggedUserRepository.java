package com.team.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTaggedUserRepository extends JpaRepository<PostTaggedUser, Long> {
}
