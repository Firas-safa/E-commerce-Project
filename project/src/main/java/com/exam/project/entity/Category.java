    package com.exam.project.entity;

    import jakarta.persistence.*;
    import lombok.Data;


    @Entity
    @Table(name = "categories")
    @Data
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true,nullable = false)
        private String title;

        private String description;

        @Column(name = "positionOrder")
        private Integer positionOrder;

    }
