package com.example.fishapp.controller;

import com.example.fishapp.dto.ImageDetailsUpdateDto;
import com.example.fishapp.dto.ImageUploadDto;
import com.example.fishapp.model.Image;
import com.example.fishapp.model.User;
import com.example.fishapp.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(path = "/upload", consumes = {"multipart/form-data"})
    public Image upload(
            @RequestAttribute @NotNull User user,
            @ModelAttribute @Valid ImageUploadDto imageUploadDto) throws Exception {
        return imageService.upload(imageUploadDto.getImage().getBytes(), user);
    }

    @GetMapping
    public List<Image> findAll() {
        return imageService.findAll();
    }

    @PutMapping("/{imageId}")
    public Image updateDetails(
            @PathVariable("imageId") @NotNull UUID imageId,
            @RequestAttribute @NotNull User user,
            @RequestBody @Valid ImageDetailsUpdateDto imageDetailsUpdateDto) {
        return imageService.updateDetails(imageId, imageDetailsUpdateDto, user);
    }

}
