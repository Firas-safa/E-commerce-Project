package com.exam.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(name = "position_order")
    private Integer positionOrder;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
}
