package com.team.user.dbutil;

import com.team.user.Follow;
import com.team.user.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class  DatabaseInsert {
    @PersistenceContext
    private EntityManager entityManager;


    public Long saveUser(User user) {
        entityManager.persist(user);
        return user.getId();
    }


    public Follow saveFollow(User follower, User followee) {
        Follow follow = Follow.builder()
                .follower(follower)
                .followee(followee)
                .build();
        entityManager.persist(follow);
        return follow;
    }

}