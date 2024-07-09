package com.trnqb.cafe.repository;

import com.trnqb.cafe.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}
