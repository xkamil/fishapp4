package com.example.fishapp.app.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dictionary_entries")
public class Entry {
    @Id
    private UUID id;

    @NotEmpty
    private String key;

    @ManyToOne
    @JsonIgnore
    private Dictionary dictionary;

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EntryTranslation> entryTranslations = new HashSet<>();

    public Entry(@NotEmpty String key, Dictionary dictionary) {
        this.id = UUID.randomUUID();
        this.key = key;
        this.dictionary = dictionary;
    }
}
