package com.example.fishapp.app.dictionary.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    private Entry entry;

    public EntryTranslation(@NotEmpty String value, @NotEmpty String locale, Entry entry) {
        this.id = UUID.randomUUID();
        this.value = value;
        this.locale = locale;
        this.entry = entry;
    }
}
