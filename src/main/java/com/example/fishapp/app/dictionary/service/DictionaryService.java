package com.example.fishapp.app.dictionary.service;

import java.util.UUID;
import com.example.fishapp.app.dictionary.dto.DictionaryCreateDto;
import com.example.fishapp.app.dictionary.dto.EntryCreateDto;
import com.example.fishapp.app.dictionary.dto.EntryTranslationCreateDto;
import com.example.fishapp.app.dictionary.model.Dictionary;
import com.example.fishapp.app.dictionary.model.EntryTranslation;
import com.example.fishapp.exception.NotFoundException;
import com.example.fishapp.app.dictionary.model.Entry;
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

    public Dictionary createDictionary(DictionaryCreateDto dictionaryCreateDto) {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(UUID.randomUUID());
        dictionary.setName(dictionaryCreateDto.getName());
        return dictionaryRepository.save(dictionary);
    }

    public Entry createEntry(UUID dictionaryId, EntryCreateDto entryCreateDto) {
        Dictionary dictionary = dictionaryRepository.findById(dictionaryId).orElseThrow(NotFoundException::new);
        Entry entry = new Entry();
        entry.setId(UUID.randomUUID());
        entry.setDictionary(dictionary);
        entry.setKey(entryCreateDto.getName());
        return dictionaryEntryRepository.save(entry);
    }

    public EntryTranslation createEntryTranslation(UUID dictionaryEntryId, EntryTranslationCreateDto entryCreateDto) {
        Entry entry = dictionaryEntryRepository.findById(dictionaryEntryId).orElseThrow(NotFoundException::new);
        EntryTranslation entryTranslation = new EntryTranslation();
        entryTranslation.setEntry(entry);
        entryTranslation.setId(UUID.randomUUID());
        entryTranslation.setLocale(entryCreateDto.getLocale());
        entryTranslation.setValue(entryCreateDto.getName());
        return dictionaryEntryTranslationRepository.save(entryTranslation);
    }
}
