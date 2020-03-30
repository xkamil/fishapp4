package com.example.fishapp.app.dictionary.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DictionaryEntryTranslationCreateCommandDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String locale;
}

