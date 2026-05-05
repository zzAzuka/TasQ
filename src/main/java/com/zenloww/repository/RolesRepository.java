package com.zenloww.repository;

import com.zenloww.common.Role;
import com.zenloww.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(Role name);
}
