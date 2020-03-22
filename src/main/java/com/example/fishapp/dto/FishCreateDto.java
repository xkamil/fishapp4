package com.example.fishapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class FishCreateDto {

    @NotNull
    private BigDecimal lat;

    @NotNull
    private BigDecimal lng;

    @NotEmpty
    private String species;

    @NotEmpty
    private BigDecimal weight;

    @NotEmpty
    private BigDecimal length;
}
