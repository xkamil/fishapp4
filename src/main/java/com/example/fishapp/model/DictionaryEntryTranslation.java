package com.example.fishapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "dictionary_entry_translations")
public class DictionaryEntryTranslation {
    @Id
    private UUID id;

    @NotEmpty
    private String value;

    @NotEmpty
    private String locale;

    @ManyToOne
    @JsonIgnore
    private DictionaryEntry dictionaryEntry;
}
