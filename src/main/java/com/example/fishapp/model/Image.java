package com.example.fishapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    private UUID id;

    @Embedded
    private ImageDetails imageDetails;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "fish_id", referencedColumnName = "id")
    private Fish fish;

    @ManyToOne
    @JsonIgnore
    private User user;

}
