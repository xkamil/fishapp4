package com.example.fishapp.app.dictionary.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dictionaries", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Dictionary {

    @Id
    private UUID id;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "dictionary")
    @JsonIgnore
    private Set<Entry> dictionaryEntries = new HashSet<>();
}
