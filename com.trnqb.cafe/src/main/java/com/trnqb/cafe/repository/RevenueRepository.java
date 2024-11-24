package com.trnqb.cafe.repository;

import com.trnqb.cafe.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {
    Revenue findByProductNameAndDate(@Param("productName") String name, @Param("date") LocalDate date);
    List<Revenue> findAllByDate(@Param("date") LocalDate date);

    Integer getMonthlyRevenue(@Param("month") int month, @Param("year") int year);

}
