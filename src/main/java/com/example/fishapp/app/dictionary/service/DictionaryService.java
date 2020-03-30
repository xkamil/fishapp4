package com.example.fishapp.app.dictionary.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.example.fishapp.app.dictionary.dto.DictionaryCreateCommandDto;
import com.example.fishapp.app.dictionary.dto.DictionaryEntryCreateCommandDto;
import com.example.fishapp.app.dictionary.dto.DictionaryEntryTranslationCreateCommandDto;
import com.example.fishapp.app.dictionary.model.Dictionary;
import com.example.fishapp.exception.NotFoundException;
import com.example.fishapp.app.dictionary.model.DictionaryEntry;
import com.example.fishapp.app.dictionary.model.DictionaryEntryTranslation;
import com.example.fishapp.app.dictionary.repository.DictionaryEntryRepository;
import com.example.fishapp.app.dictionary.repository.DictionaryEntryTranslationRepository;
import com.example.fishapp.app.dictionary.repository.DictionaryRepository;
import org.springframework.stereotype.Service;


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

    public Dictionary createDictionary(DictionaryCreateCommandDto dictionaryCreateCommandDto) {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(UUID.randomUUID());
        dictionary.setName(dictionaryCreateCommandDto.getName());
        return dictionaryRepository.save(dictionary);
    }

    public DictionaryEntry createDictionaryEntry(
            UUID dictionaryId,
            DictionaryEntryCreateCommandDto dictionaryEntryCreateCommandDto) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId).orElseThrow(NotFoundException::new);
        DictionaryEntry dictionaryEntry = new DictionaryEntry();
        dictionaryEntry.setId(UUID.randomUUID());
        dictionaryEntry.setDictionary(dictionary);
        dictionaryEntry.setKey(dictionaryEntryCreateCommandDto.getName());
        return dictionaryEntryRepository.save(dictionaryEntry);
    }


    public DictionaryEntryTranslation createDictionaryEntryTranslation(
            UUID dictionaryEntryId,
            DictionaryEntryTranslationCreateCommandDto dictionaryEntryCreateDto) {
        DictionaryEntry dictionaryEntry = dictionaryEntryRepository.findById(dictionaryEntryId).orElseThrow(NotFoundException::new);
        DictionaryEntryTranslation dictionaryEntryTranslation = new DictionaryEntryTranslation();
        dictionaryEntryTranslation.setDictionaryEntry(dictionaryEntry);
        dictionaryEntryTranslation.setId(UUID.randomUUID());
        dictionaryEntryTranslation.setLocale(dictionaryEntryCreateDto.getLocale());
        dictionaryEntryTranslation.setValue(dictionaryEntryCreateDto.getName());
        return dictionaryEntryTranslationRepository.save(dictionaryEntryTranslation);
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
