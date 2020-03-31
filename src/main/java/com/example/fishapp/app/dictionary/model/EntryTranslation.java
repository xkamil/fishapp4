package com.example.fishapp.app.dictionary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "dictionary_entry_translations")
public class EntryTranslation {
    @Id
    private UUID id;

    @NotEmpty
    private String value;

    @NotEmpty
    private String locale;

    @ManyToOne
    @JsonIgnore
    private Entry entry;

    public EntryTranslation(@NotEmpty String value, @NotEmpty String locale, Entry entry) {
        this.id = UUID.randomUUID();
        this.value = value;
        this.locale = locale;
        this.entry = entry;
    }
}
