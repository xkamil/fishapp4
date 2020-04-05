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
@Table(name = "dictionaries", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Dictionary {

    @Id
    private UUID id;

    @NotEmpty
    private String name;

    public Dictionary(@NotEmpty String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
