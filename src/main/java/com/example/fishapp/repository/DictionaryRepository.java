package com.example.fishapp.repository;

import com.example.fishapp.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, UUID> {
    Optional<Dictionary> findByName(String name);
}
