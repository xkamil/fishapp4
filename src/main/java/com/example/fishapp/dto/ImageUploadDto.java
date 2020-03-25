package com.example.fishapp.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class ImageUploadDto {

    @NotNull
    private MultipartFile image;

}
