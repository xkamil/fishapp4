package com.example.fishapp.model;

import com.example.fishapp.app.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
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

    @OneToOne(mappedBy = "fish")
    private Image image;

    @ManyToOne
    @JsonIgnore
    private User user;

}
