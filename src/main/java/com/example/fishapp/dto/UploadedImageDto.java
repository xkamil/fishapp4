package com.example.fishapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadedImageDto {

    private String original;
    private String small;
    private String medium;
    private String large;
}
