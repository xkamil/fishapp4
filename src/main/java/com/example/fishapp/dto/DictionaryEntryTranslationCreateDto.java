package com.example.fishapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DictionaryEntryTranslationCreateDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String locale;
}

