package com.example.fishapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fishes")
public class Fish {
    @Id
    private UUID id;

    private BigDecimal lat;

    private BigDecimal lng;

    private String species;

    private BigDecimal weight;

    private BigDecimal length;

    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    private User user;

}
