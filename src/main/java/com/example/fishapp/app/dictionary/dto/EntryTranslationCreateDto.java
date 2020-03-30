package com.example.fishapp.app.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryTranslationCreateDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String locale;
}

