package com.example.fishapp.repository;

import com.example.fishapp.model.DictionaryEntryTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DictionaryEntryTranslationRepository extends JpaRepository<DictionaryEntryTranslation, UUID> {

}
