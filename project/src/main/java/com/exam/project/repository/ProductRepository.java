package com.exam.project.repository;

import com.exam.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT COALESCE(MAX(p.positionOrder), 0) FROM Product p")
    Integer findMaxPositionOrder();
    boolean existsByTitle(String title);

    List<Product> findByCategoryTitle(String categoryTitle);
}
