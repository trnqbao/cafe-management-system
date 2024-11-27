package com.trnqb.cafe.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

//@NamedQuery(name = "Customer.updateCustomerDiscount", query = "update Customer c set c.isDiscount=:isDiscount where c.id=:id")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "point")
    private Integer point;

    @Column(name = "isDiscount")
    private Boolean isDiscount;

    @Column(name = "last_order")
    private LocalDate lastOrder;
}
