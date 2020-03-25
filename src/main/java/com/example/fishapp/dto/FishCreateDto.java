package com.example.fishapp.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class FishCreateDto {

    @NotNull
    private UUID imageId;

    @NotNull
    private BigDecimal lat;

    @NotNull
    private BigDecimal lng;

    @NotEmpty
    private String species;

    @NotNull
    private BigDecimal weight;

    @NotNull
    private BigDecimal length;
}
