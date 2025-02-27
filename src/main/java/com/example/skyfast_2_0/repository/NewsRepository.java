package com.example.skyfast_2_0.repository;

import com.example.skyfast_2_0.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    // Additional query methods can be defined here if needed
}