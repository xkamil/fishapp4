package com.example.fishapp.app.dictionary.repository;

import com.example.fishapp.app.dictionary.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface DictionaryEntryRepository extends JpaRepository<Entry, UUID> {

}
