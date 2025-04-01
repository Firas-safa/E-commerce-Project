package com.exam.project.repository;

import com.exam.project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT COALESCE(MAX(c.positionOrder), 0) FROM Category c")
    Integer findMaxPositionOrder();
    boolean existsByTitle(String title);
}
