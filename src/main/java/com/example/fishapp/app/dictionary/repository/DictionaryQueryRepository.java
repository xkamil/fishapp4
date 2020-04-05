package com.example.fishapp.app.dictionary.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.fishapp.app.dictionary.model.QDictionary;
import com.example.fishapp.app.dictionary.model.QEntry;
import com.example.fishapp.app.dictionary.model.QEntryTranslation;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class  DictionaryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public DictionaryQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Map<String, String> findByNameAndLocale(String name, String locale) {
        QDictionary dictionary = QDictionary.dictionary;
        QEntry entry = QEntry.entry;
        QEntryTranslation entryTranslation = QEntryTranslation.entryTranslation;

        List<Tuple> entries = queryFactory.select(entry.key, entryTranslation.value)
                .from(dictionary)
                .innerJoin(entry).on(entry.dictionary.eq(dictionary))
                .leftJoin(entryTranslation).on(entryTranslation.entry.eq(entry).and(entryTranslation.locale.eq(locale)))
                .where(dictionary.name.eq(name))
                .fetch();

        return entries.stream()
                .collect(Collectors.toMap(
                        k -> k.get(entry.key),
                        v -> Optional.ofNullable(v.get(entryTranslation.value)).orElse("")));
    }


}