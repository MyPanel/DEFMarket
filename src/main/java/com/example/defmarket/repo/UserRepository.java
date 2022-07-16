package com.example.defmarket.repo;

import com.example.defmarket.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    // @PersistenceContext // 1. JPA 사용 표준 어노테이션
    private final EntityManager em; // 2. 이렇게 해주면 스프링이 자동으로 엔티티 매니저를 만들어서 em에 주입시켜준다.

    public void save(User user){ //저장
        em.persist(user);
    }

    public User findByUsername(String username) {
        return em.find(User.class, username);
    }
}
