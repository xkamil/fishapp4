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
    private Set<DictionaryEntry> dictionaryEntries = new HashSet<>();
}
