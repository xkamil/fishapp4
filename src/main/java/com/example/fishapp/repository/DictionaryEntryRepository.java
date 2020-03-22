package com.example.fishapp.repository;

import com.example.fishapp.model.DictionaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DictionaryEntryRepository extends JpaRepository<DictionaryEntry, UUID> {

}
