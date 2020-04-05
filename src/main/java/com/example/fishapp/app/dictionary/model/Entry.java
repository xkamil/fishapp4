package com.example.fishapp.app.dictionary.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Data
@NoArgsConstructor
@Entity
@Transactional
@Table(name = "dictionary_entries")
public class Entry {
    @Id
    private UUID id;

    @NotEmpty
    private String key;

    @ManyToOne
    private Dictionary dictionary;

    public Entry(@NotEmpty String key, Dictionary dictionary) {
        this.id = UUID.randomUUID();
        this.key = key;
        this.dictionary = dictionary;
    }
}
