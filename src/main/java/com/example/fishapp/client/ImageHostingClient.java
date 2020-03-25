package com.example.fishapp.client;

import com.example.fishapp.dto.UploadedImageDto;

public interface ImageHostingClient {

    UploadedImageDto uploadImage(byte[] bytes);
}
