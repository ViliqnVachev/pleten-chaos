package com.pletenchaos.pletenchaos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pletenchaos.pletenchaos.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByloginName(String username);
}
