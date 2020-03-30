package com.example.fishapp;

import com.example.fishapp.app.dictionary.dto.DictionaryCreateDto;
import com.example.fishapp.app.dictionary.dto.EntryCreateDto;
import com.example.fishapp.app.dictionary.dto.EntryTranslationCreateDto;
import com.example.fishapp.app.dictionary.model.Dictionary;
import com.example.fishapp.app.dictionary.model.Entry;
import com.example.fishapp.app.dictionary.service.DictionaryService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initializer {
    private final DictionaryService dictionaryService;

    public Initializer(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
        initDictionaries();
    }

    private void initDictionaries() {
        DictionaryCreateDto dictionaryCreateDto = new DictionaryCreateDto("species");
        Dictionary dictionary1 = dictionaryService.createDictionary(dictionaryCreateDto);

        EntryCreateDto entryCreateDto = new EntryCreateDto("BARRACUDA");
        Entry entry = dictionaryService.createEntry(dictionary1.getId(), entryCreateDto);

        EntryTranslationCreateDto entryTranslationCreateDto1 = new EntryTranslationCreateDto("Barakuda", "PL");
        dictionaryService.createEntryTranslation(entry.getId(), entryTranslationCreateDto1);

        EntryTranslationCreateDto entryTranslationCreateDto2 = new EntryTranslationCreateDto("Barracuda", "EN");
        dictionaryService.createEntryTranslation(entry.getId(), entryTranslationCreateDto2);

    }
}
