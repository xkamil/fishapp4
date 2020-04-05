package com.example.fishapp.app.dictionary.service;

import java.util.Map;
import com.example.fishapp.app.dictionary.repository.DictionaryQueryRepository;
import com.example.fishapp.app.dictionary.repository.DictionaryRepository;
import com.example.fishapp.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DictionaryQueryService {
    private static final Logger log = LoggerFactory.getLogger(DictionaryQueryService.class);

    private final DictionaryQueryRepository dictionaryQueryRepository;
    private final DictionaryRepository dictionaryRepository;

    public DictionaryQueryService(DictionaryQueryRepository dictionaryQueryRepository, DictionaryRepository dictionaryRepository) {
        this.dictionaryQueryRepository = dictionaryQueryRepository;
        this.dictionaryRepository = dictionaryRepository;
    }

    public Map<String, String> findByNameAndLocale(String name, String locale) {
        dictionaryRepository.findByName(name).orElseThrow(NotFoundException::new);
        return dictionaryQueryRepository.findByNameAndLocale(name, locale);
    }

}
