package com.example.fishapp.app.dictionary.service;

import java.util.UUID;
import com.example.fishapp.app.dictionary.model.Dictionary;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class DictionaryCommandServiceTest {

    @Autowired
    private DictionaryCommandService dictionaryCommandService;

    private final String csv = "KEY, PL, EN\n" +
            "KEY1, val1pl, val1en\n" +
            "KEY2, val2pl,\n" +
            "KEY3, , val2en";

    @Test
    void shouldCreateDictionary() {
        //given
        String dictionaryName = UUID.randomUUID().toString();

        //when
        Dictionary dictionary = dictionaryCommandService.createDictionary(dictionaryName, csv);

        //then
        assertThat(dictionary.getId()).isNotNull();
        assertThat(dictionary.getName()).isEqualTo(dictionaryName);
    }
}
