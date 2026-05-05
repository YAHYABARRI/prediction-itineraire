package com.example.demo.repository;



import com.example.demo.models.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LieuRepository extends JpaRepository<Lieu, Long> {
}