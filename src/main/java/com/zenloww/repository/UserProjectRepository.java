package com.zenloww.repository;

import com.zenloww.entity.User;
import com.zenloww.entity.UserProjectMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProjectRepository extends JpaRepository<UserProjectMembership, Integer> {
    boolean existsByProjectidAndUserid(Integer projectid, Integer userid);
}
