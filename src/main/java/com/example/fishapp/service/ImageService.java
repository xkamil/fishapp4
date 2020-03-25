package com.example.fishapp.service;

import com.example.fishapp.client.ImageHostingClient;
import com.example.fishapp.dto.ImageDetailsUpdateDto;
import com.example.fishapp.dto.UploadedImageDto;
import com.example.fishapp.exception.ForbiddenException;
import com.example.fishapp.exception.NotFoundException;
import com.example.fishapp.model.Image;
import com.example.fishapp.model.ImageDetails;
import com.example.fishapp.model.Role;
import com.example.fishapp.model.User;
import com.example.fishapp.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageHostingClient imageHostingClient;
    private final ImageRepository imageRepository;

    public ImageService(ImageHostingClient imageHostingClient, ImageRepository imageRepository) {
        this.imageHostingClient = imageHostingClient;
        this.imageRepository = imageRepository;
    }

    public Image upload(byte[] image, User user) {
        UploadedImageDto uploadedImageDto = imageHostingClient.uploadImage(image);
        Image img = new Image();
        img.setId(UUID.randomUUID());
        img.setUser(user);
        ImageDetails imageDetails = new ImageDetails();
        imageDetails.setOriginal(uploadedImageDto.getOriginal());
        imageDetails.setLarge(uploadedImageDto.getLarge());
        imageDetails.setMedium(uploadedImageDto.getMedium());
        imageDetails.setSmall(uploadedImageDto.getSmall());
        img.setImageDetails(imageDetails);
        return imageRepository.save(img);
    }

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Image findById(UUID imageId) {
        return imageRepository.findById(imageId).orElseThrow(NotFoundException::new);
    }

    public Image updateDetails(UUID imageId, ImageDetailsUpdateDto imageDetailsUpdateDto, User user) {
        Image img = imageRepository.findById(imageId).orElseThrow(NotFoundException::new);

        if (!img.getUser().getId().equals(user.getId()) && !user.getRole().equals(Role.ADMIN)) {
            throw new ForbiddenException();
        }

        img.getImageDetails().setCrop(imageDetailsUpdateDto.getCrop());
        return imageRepository.save(img);
    }
}
