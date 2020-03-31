package com.example.fishapp;

import com.example.fishapp.app.dictionary.service.DictionaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    void contextLoads() {
//        DictionaryCreateDto dictionaryCreateDto = new DictionaryCreateDto(UUID.randomUUID().toString());
//        Dictionary dictionary = dictionaryService.createDictionary(dictionaryCreateDto);
//
//        assertNotNull(dictionary.getId());
    }

}
