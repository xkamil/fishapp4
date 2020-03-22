package com.example.fishapp.service;

import com.example.fishapp.dto.DictionaryCreateDto;
import com.example.fishapp.dto.DictionaryEntryCreateDto;
import com.example.fishapp.dto.DictionaryEntryTranslationCreateDto;
import com.example.fishapp.exception.NotFoundException;
import com.example.fishapp.model.Dictionary;
import com.example.fishapp.model.DictionaryEntry;
import com.example.fishapp.model.DictionaryEntryTranslation;
import com.example.fishapp.repository.DictionaryEntryRepository;
import com.example.fishapp.repository.DictionaryEntryTranslationRepository;
import com.example.fishapp.repository.DictionaryRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class DictionaryService {

    private final DictionaryRepository dictionaryRepository;
    private final DictionaryEntryRepository dictionaryEntryRepository;
    private final DictionaryEntryTranslationRepository dictionaryEntryTranslationRepository;

    public DictionaryService(
            DictionaryRepository dictionaryRepository,
            DictionaryEntryRepository dictionaryEntryRepository,
            DictionaryEntryTranslationRepository dictionaryEntryTranslationRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryEntryRepository = dictionaryEntryRepository;
        this.dictionaryEntryTranslationRepository = dictionaryEntryTranslationRepository;
    }

    public Dictionary createDictionary(DictionaryCreateDto dictionaryCreateDto) {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(UUID.randomUUID());
        dictionary.setName(dictionaryCreateDto.getName());
        return dictionaryRepository.save(dictionary);
    }

    public DictionaryEntry createDictionaryEntry(
            UUID dictionaryId,
            DictionaryEntryCreateDto dictionaryEntryCreateDto) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId).orElseThrow(NotFoundException::new);
        DictionaryEntry dictionaryEntry = new DictionaryEntry();
        dictionaryEntry.setId(UUID.randomUUID());
        dictionaryEntry.setDictionary(dictionary);
        dictionaryEntry.setKey(dictionaryEntryCreateDto.getName());
        return dictionaryEntryRepository.save(dictionaryEntry);
    }


    public DictionaryEntryTranslation createDictionaryEntryTranslation(
            UUID dictionaryEntryId,
            DictionaryEntryTranslationCreateDto dictionaryEntryCreateDto) {
        DictionaryEntry dictionaryEntry = dictionaryEntryRepository.findById(dictionaryEntryId).orElseThrow(NotFoundException::new);
        DictionaryEntryTranslation dictionaryEntryTranslation = new DictionaryEntryTranslation();
        dictionaryEntryTranslation.setDictionaryEntry(dictionaryEntry);
        dictionaryEntryTranslation.setId(UUID.randomUUID());
        dictionaryEntryTranslation.setLocale(dictionaryEntryCreateDto.getLocale());
        dictionaryEntryTranslation.setValue(dictionaryEntryCreateDto.getName());
        return dictionaryEntryTranslationRepository.save(dictionaryEntryTranslation);
    }

    public Dictionary findByName(String dictionaryName) {
        return dictionaryRepository.findByName(dictionaryName).orElseThrow(NotFoundException::new);
    }

    public Map<String, String> findByNameAndLocale(String dictionaryName, String locale) {
        Dictionary dictionary = dictionaryRepository.findByName(dictionaryName).orElseThrow(NotFoundException::new);
        Map<String, String> values = new HashMap<>();
        dictionary.getDictionaryEntries().forEach(d -> {
            String key = d.getKey();
            String value = d.getDictionaryEntryTranslations().stream()
                    .filter(it -> it.getLocale().equals(locale))
                    .findFirst()
                    .map(DictionaryEntryTranslation::getValue)
                    .orElse("");
            values.put(key, value);
        });
        return values;
    }
}
