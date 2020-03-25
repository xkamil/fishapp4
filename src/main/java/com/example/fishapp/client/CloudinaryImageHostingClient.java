package com.example.fishapp.client;

import com.cloudinary.Cloudinary;
import com.example.fishapp.dto.UploadedImageDto;
import com.example.fishapp.exception.ImageUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryImageHostingClient implements ImageHostingClient {
    private static final Logger log = LoggerFactory.getLogger(CloudinaryImageHostingClient.class);

    private final Cloudinary cloudinary;

    public CloudinaryImageHostingClient(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public UploadedImageDto uploadImage(byte[] image) throws ImageUploadException {
        Map<String, String> uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(image, Map.of());
            log.info("Uploaded image result: \n{}", uploadResult);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageUploadException(e);
        }
        UploadedImageDto uploadedImageDto = new UploadedImageDto();
        uploadedImageDto.setOriginal("url");
        uploadedImageDto.setSmall(createImageUrl(uploadResult.get("url"), 300));
        uploadedImageDto.setMedium(createImageUrl(uploadResult.get("url"), 600));
        uploadedImageDto.setLarge(createImageUrl(uploadResult.get("url"), 1000));
        return uploadedImageDto;
    }

    private String createImageUrl(String originalUrl, int targetWidth) {
        return originalUrl.replace("/upload", "/upload/w_" + targetWidth);
    }

}
