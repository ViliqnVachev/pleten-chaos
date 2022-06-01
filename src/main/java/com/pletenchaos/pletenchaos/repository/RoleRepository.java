package com.pletenchaos.pletenchaos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pletenchaos.pletenchaos.model.entity.RoleEntity;
import com.pletenchaos.pletenchaos.model.entity.enums.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	Optional<RoleEntity> findByRole(RoleEnum role);

}
