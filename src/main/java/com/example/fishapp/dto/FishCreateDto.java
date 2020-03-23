package com.example.fishapp.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class FishCreateDto {

    @NotNull
    private MultipartFile image;

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
