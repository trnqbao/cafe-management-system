package com.trnqb.cafe.repository;

import com.trnqb.cafe.entities.Role;
import com.trnqb.cafe.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(@Param("email") String email);

    User findByRole(Role role);

    List<User> findAllByRole(Role role);

    List<String> getAllAdmin();

    Role findRoleByEmail(String email);

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
}

