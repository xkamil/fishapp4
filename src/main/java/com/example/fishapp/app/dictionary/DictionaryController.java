package com.example.fishapp.app.dictionary;

import com.example.fishapp.app.dictionary.dto.DictionaryCreateCommandDto;
import com.example.fishapp.app.dictionary.model.Dictionary;
import com.example.fishapp.app.dictionary.model.DictionaryEntry;
import com.example.fishapp.app.dictionary.model.DictionaryEntryTranslation;
import com.example.fishapp.app.dictionary.dto.DictionaryEntryCreateCommandDto;
import com.example.fishapp.app.dictionary.dto.DictionaryEntryTranslationCreateCommandDto;
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
        return dictionaryQueryRepository.findByDictionaryNameAndLocale(name, locale);
    }

    @PostMapping
    public Dictionary createDictionary(@RequestBody @Valid DictionaryCreateCommandDto dictionaryCreateCommandDto) {
        return dictionaryService.createDictionary(dictionaryCreateCommandDto);
    }

    @PostMapping("/{dictionaryId}/entries")
    public DictionaryEntry createDictionaryEntry(
            @PathVariable("dictionaryId") UUID dictionaryId,
            @RequestBody @Valid DictionaryEntryCreateCommandDto dictionaryEntryCreateCommandDto) {
        return dictionaryService.createDictionaryEntry(dictionaryId, dictionaryEntryCreateCommandDto);
    }

    @PostMapping("/entries/{entryId}/translations")
    public DictionaryEntryTranslation createEntry(
            @PathVariable("entryId") UUID dictionaryEntryId,
            @RequestBody @Valid DictionaryEntryTranslationCreateCommandDto dictionaryEntryTranslation) {
        return dictionaryService.createDictionaryEntryTranslation(dictionaryEntryId, dictionaryEntryTranslation);
    }


}
