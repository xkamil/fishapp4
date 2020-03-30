package com.example.fishapp.app.dictionary.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.fishapp.app.dictionary.model.QDictionary;
import com.example.fishapp.app.dictionary.model.QDictionaryEntry;
import com.example.fishapp.app.dictionary.model.QDictionaryEntryTranslation;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DictionaryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public DictionaryQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Map<String, String> findByDictionaryNameAndLocale(String name, String locale) {
        QDictionary dictionary = QDictionary.dictionary;
        QDictionaryEntry entry = QDictionaryEntry.dictionaryEntry;
        QDictionaryEntryTranslation entryTranslation = QDictionaryEntryTranslation.dictionaryEntryTranslation;

        List<Tuple> entries = queryFactory.select(entry.key, entryTranslation.value)
                .from(dictionary)
                .innerJoin(entry).on(entry.dictionary.eq(dictionary))
                .leftJoin(entryTranslation).on(entryTranslation.dictionaryEntry.eq(entry).and(entryTranslation.locale.eq(locale)))
                .fetch();

        return entries.stream()
                .collect(Collectors.toMap(
                        k -> k.get(entry.key),
                        v -> Optional.ofNullable(v.get(entryTranslation.value)).orElse("")));
    }


}