package com.example.fishapp.controller;

import com.example.fishapp.model.Dictionary;
import com.example.fishapp.model.DictionaryEntry;
import com.example.fishapp.model.DictionaryEntryTranslation;
import com.example.fishapp.dto.DictionaryCreateDto;
import com.example.fishapp.dto.DictionaryEntryCreateDto;
import com.example.fishapp.dto.DictionaryEntryTranslationCreateDto;
import com.example.fishapp.exception.NotFoundException;
import com.example.fishapp.repository.DictionaryEntryRepository;
import com.example.fishapp.repository.DictionaryEntryTranslationRepository;
import com.example.fishapp.repository.DictionaryRepository;
import com.example.fishapp.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/dictionaries")
public class DictionaryController {
    private static final Logger log = LoggerFactory.getLogger(DictionaryController.class);

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/find")
    public Map<String, String> find(
            @RequestParam @NotEmpty String name,
            @RequestParam @NotEmpty String locale) {
        return dictionaryService.findByNameAndLocale(name, locale);
    }

    @PostMapping
    public Dictionary createDictionary(@RequestBody @Valid DictionaryCreateDto dictionaryCreateDto) {
        return dictionaryService.createDictionary(dictionaryCreateDto);
    }

    @PostMapping("/{dictionaryId}/entries")
    public DictionaryEntry createDictionaryEntry(
            @PathVariable("dictionaryId") UUID dictionaryId,
            @RequestBody @Valid DictionaryEntryCreateDto dictionaryEntryCreateDto) {
        return dictionaryService.createDictionaryEntry(dictionaryId, dictionaryEntryCreateDto);
    }

    @PostMapping("/entries/{entryId}/translations")
    public DictionaryEntryTranslation createEntry(
            @PathVariable("entryId") UUID dictionaryEntryId,
            @RequestBody @Valid DictionaryEntryTranslationCreateDto dictionaryEntryTranslation) {
        return dictionaryService.createDictionaryEntryTranslation(dictionaryEntryId, dictionaryEntryTranslation);
    }


}
