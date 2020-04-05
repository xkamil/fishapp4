package com.example.fishapp.app.dictionary.service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;
import com.example.fishapp.app.dictionary.repository.DictionaryRepository;
import com.example.fishapp.exception.NotFoundException;
import com.google.common.io.Resources;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class DictionaryQueryServiceTest {

    @Autowired
    private DictionaryCommandService dictionaryCommandService;

    @Autowired
    private DictionaryQueryService dictionaryQueryService;

    private String dictionaryName;
    private static String csv;

    @BeforeAll
    static void beforeAll() throws Exception {
        URL url = Resources.getResource("dictionary.csv");
        csv = Resources.toString(url, StandardCharsets.UTF_8);
    }

    @BeforeEach
    void beforeEach() {
        dictionaryName = UUID.randomUUID().toString();
        dictionaryCommandService.createDictionary(dictionaryName, csv);
    }

    @Test
    void shouldReturnDictionary() {
        //when
        Map<String, String> entries = dictionaryQueryService.findByNameAndLocale(dictionaryName, "PL");

        //then
        assertThat(entries.keySet()).containsExactlyInAnyOrder("KEY1", "KEY2", "KEY3");
        assertThat(entries.get("KEY1")).isEqualTo("val1pl");
        assertThat(entries.get("KEY2")).isEqualTo("val2pl");
        assertThat(entries.get("KEY3")).isEqualTo("");
    }

    @Test
    void shouldThrow() {
        //given
        String dictionaryName = UUID.randomUUID().toString();

        //when
        assertThrows(NotFoundException.class, () -> {
            dictionaryQueryService.findByNameAndLocale(dictionaryName, "PL");
        });
    }
}
