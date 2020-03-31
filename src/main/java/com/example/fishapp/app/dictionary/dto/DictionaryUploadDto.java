package com.example.fishapp.app.dictionary.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class DictionaryUploadDto {

    @NotNull
    private MultipartFile dictionary;

}
