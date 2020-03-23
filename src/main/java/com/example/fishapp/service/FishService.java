package com.example.fishapp.service;

import com.cloudinary.Cloudinary;
import com.example.fishapp.dto.FishCreateDto;
import com.example.fishapp.model.Fish;
import com.example.fishapp.repository.FishRepository;
import com.example.fishapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FishService {
    private static final Logger log = LoggerFactory.getLogger(FishService.class);

    private final FishRepository fishRepository;
    private final Cloudinary cloudinary;

    public FishService(FishRepository fishRepository, Cloudinary cloudinary) {
        this.fishRepository = fishRepository;
        this.cloudinary = cloudinary;
    }

    public Fish createFish(FishCreateDto fishCreateDto, User userEntity) throws IOException {
        Map<String, String> uploadResult = cloudinary.uploader().upload(fishCreateDto.getImage().getBytes(), new HashMap());
        log.info("Upload result: {}", uploadResult);

        Fish fish = new Fish();
        fish.setId(UUID.randomUUID());
        fish.setLat(fishCreateDto.getLat());
        fish.setLng(fishCreateDto.getLng());
        fish.setLength(fishCreateDto.getLength());
        fish.setWeight(fishCreateDto.getWeight());
        fish.setSpecies(fishCreateDto.getSpecies());
        fish.setUser(userEntity);
        fish.setImageUrl(uploadResult.get("url"));
        return fishRepository.save(fish);
    }

    public List<Fish> findFishes(List<UUID> usersIds, BigDecimal range) {
        return fishRepository.findAll().stream()
                .filter(fish -> usersIds.contains(fish.getUser().getId()))
                .filter(fish -> fish.getLat().add(range).compareTo(fish.getLat()) <= 0 && fish.getLng().add(range).compareTo(range) <= 0)
                .collect(Collectors.toList());
    }
}
