package com.example.fishapp.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
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
public class DictionaryEntry {
    @Id
    private UUID id;

    @NotEmpty
    private String key;

    @ManyToOne
    @JsonIgnore
    private Dictionary dictionary;

    @OneToMany(mappedBy = "dictionaryEntry")
    @JsonIgnore
    private Set<DictionaryEntryTranslation> dictionaryEntryTranslations = new HashSet<>();
}
