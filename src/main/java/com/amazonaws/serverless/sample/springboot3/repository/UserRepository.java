package com.amazonaws.serverless.sample.springboot3.repository;

import com.amazonaws.serverless.sample.springboot3.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
