package com.example.fishapp.app.dictionary.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.example.fishapp.app.dictionary.model.Dictionary;
import com.example.fishapp.app.dictionary.model.EntryTranslation;
import com.example.fishapp.app.dictionary.model.Entry;
import com.example.fishapp.app.dictionary.repository.DictionaryEntryRepository;
import com.example.fishapp.app.dictionary.repository.DictionaryEntryTranslationRepository;
import com.example.fishapp.app.dictionary.repository.DictionaryRepository;
import org.springframework.stereotype.Service;


@Service
public class DictionaryService {

    private static final String CSV_VALUE_SEPARATOR = ",";

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

    public void createDictionary(String name, String csvFileContent) {
        dictionaryRepository.findByName(name).ifPresent(dictionaryRepository::delete);

        List<String> csvFileLines = Arrays.asList(csvFileContent.split("\n"));
        Dictionary dictionary = dictionaryRepository.save(new Dictionary(name));

        List<String> locales = Arrays.stream(csvFileLines.get(0).split(CSV_VALUE_SEPARATOR))
                .map(String::trim)
                .skip(1)
                .collect(Collectors.toList());

        csvFileLines.stream()
                .skip(1)
                .forEach(line -> createEntryAndTranslations(dictionary, line, locales));
    }

    private void createEntryAndTranslations(Dictionary dictionary, String line, List<String> locales) {
        String name = line.split(CSV_VALUE_SEPARATOR)[0].trim();

        List<String> translations = Arrays.asList(line.split(CSV_VALUE_SEPARATOR)).stream()
                .map(String::trim)
                .skip(1)
                .collect(Collectors.toList());

        Entry entry = dictionaryEntryRepository.save(new Entry(name, dictionary));
        createEntryTranslations(entry, translations, locales);
    }

    private void createEntryTranslations(Entry entry, List<String> translations, List<String> locales) {
        for (int i = 0; i < translations.size(); i++) {
            if (translations.get(i).length() > 0) {
                dictionaryEntryTranslationRepository.save(new EntryTranslation(translations.get(i), locales.get(i), entry));
            }
        }
    }
}
