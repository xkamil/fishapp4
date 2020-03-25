package com.example.fishapp.exception;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException(Exception e) {
        super(e);
    }
}
