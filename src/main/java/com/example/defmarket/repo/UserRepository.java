package com.example.defmarket.repo;

import com.example.defmarket.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @RequiredArgsConstructor
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserEmail(String userEmail);

    // @PersistenceContext // 1. JPA 사용 표준 어노테이션
    // private final EntityManager em; // 2. 이렇게 해주면 스프링이 자동으로 엔티티 매니저를 만들어서 em에 주입시켜준다.
}
