package com.example.fishapp.repository;

import com.example.fishapp.model.Image;
import com.example.fishapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    List<Image> findAllByUserId(UUID uuid);
}
