package com.example.fishapp.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class ImageDetails {

    private String original;
    private String small;
    private String medium;
    private String large;
    private String crop;
}
