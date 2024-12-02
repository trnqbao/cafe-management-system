package com.trnqb.cafe.repository;

import com.trnqb.cafe.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findAllByCreateBy(@Param("username") String currentUser);

    List<Bill> findAllByDateRanges(@Param("startDate") Date startDate);

    List<Bill> findAllByDate(Date date);

    List<Bill> findAllByPaymentMethod(String payment);
}
