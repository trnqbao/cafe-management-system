package com.trnqb.cafe.repository;

import com.trnqb.cafe.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findAllByCreateBy(@Param("username") String currentUser);
}
