package com.trnqb.cafe.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@NamedQuery(name = "Revenue.getMonthlyRevenue", query = "SELECT COALESCE (SUM(r.total), 0) FROM Revenue r WHERE YEAR(r.date) = :year AND MONTH(r.date) = :month")
@NamedQuery(name = "Revenue.findFrequentProductsLast7Days",
        query = "SELECT p.productName, SUM(p.quantity) AS productCount " +
                "FROM Revenue p " +
                "WHERE p.date >= :startDate " +
                "GROUP BY p.productName " +
                "ORDER BY productCount DESC")
@Data
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "revenue")
public class Revenue {
    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_quantity")
    private Integer quantity;

    @Column(name = "total")
    private Integer total;

    @Column(name = "date")
    private LocalDate date;
}
