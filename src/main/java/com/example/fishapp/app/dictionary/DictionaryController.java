package com.example.fishapp.app.dictionary;

import com.example.fishapp.app.dictionary.dto.DictionaryCreateDto;
import com.example.fishapp.app.dictionary.model.Dictionary;
import com.example.fishapp.app.dictionary.model.Entry;
import com.example.fishapp.app.dictionary.model.EntryTranslation;
import com.example.fishapp.app.dictionary.dto.EntryCreateDto;
import com.example.fishapp.app.dictionary.dto.EntryTranslationCreateDto;
import com.example.fishapp.app.dictionary.repository.DictionaryQueryRepository;
import com.example.fishapp.app.dictionary.service.DictionaryService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/dictionaries")
public class DictionaryController {

    private final DictionaryService dictionaryService;
    private final DictionaryQueryRepository dictionaryQueryRepository;

    public DictionaryController(DictionaryService dictionaryService, DictionaryQueryRepository dictionaryQueryRepository) {
        this.dictionaryService = dictionaryService;
        this.dictionaryQueryRepository = dictionaryQueryRepository;
    }

    @GetMapping("/find")
    public Map<String, String> find(
            @RequestParam @NotEmpty String name,
            @RequestParam @NotEmpty String locale) {
        return dictionaryQueryRepository.findByNameAndLocale(name, locale);
    }

    @PostMapping
    public Dictionary createDictionary(@RequestBody @Valid DictionaryCreateDto dictionaryCreateDto) {
        return dictionaryService.createDictionary(dictionaryCreateDto);
    }

    @PostMapping("/{dictionaryId}/entries")
    public Entry createDictionaryEntry(
            @PathVariable("dictionaryId") UUID dictionaryId,
            @RequestBody @Valid EntryCreateDto entryCreateDto) {
        return dictionaryService.createEntry(dictionaryId, entryCreateDto);
    }

    @PostMapping("/entries/{entryId}/translations")
    public EntryTranslation createEntry(
            @PathVariable("entryId") UUID dictionaryEntryId,
            @RequestBody @Valid EntryTranslationCreateDto dictionaryEntryTranslation) {
        return dictionaryService.createEntryTranslation(dictionaryEntryId, dictionaryEntryTranslation);
    }


}
