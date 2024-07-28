package com.productservice.userservice.repositories;

import com.productservice.userservice.model.Role;
import com.productservice.userservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByTokenAndUser_Id(String token, Long userId);
    //select * from sessions where token = <> and userId = <>
}
