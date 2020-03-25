package com.example.fishapp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.example.fishapp.dto.FishCreateDto;
import com.example.fishapp.model.Fish;
import com.example.fishapp.model.Image;
import com.example.fishapp.model.User;
import com.example.fishapp.repository.FishRepository;
import org.springframework.stereotype.Service;

@Service
public class FishService {
    private final FishRepository fishRepository;
    private final ImageService imageService;

    public FishService(FishRepository fishRepository, ImageService imageService) {
        this.fishRepository = fishRepository;
        this.imageService = imageService;
    }

    public Fish createFish(FishCreateDto fishCreateDto, User userEntity) {
        Fish fish = new Fish();
        fish.setId(UUID.randomUUID());
        fish.setLat(fishCreateDto.getLat());
        fish.setLng(fishCreateDto.getLng());
        fish.setLength(fishCreateDto.getLength());
        fish.setWeight(fishCreateDto.getWeight());
        fish.setSpecies(fishCreateDto.getSpecies());
        fish.setUser(userEntity);
        Image img = imageService.findById(fishCreateDto.getImageId());
        fish.setImage(img);
        return fishRepository.save(fish);
    }

    public List<Fish> findFishes(List<UUID> usersIds, BigDecimal range) {
        return fishRepository.findAll().stream()
                .filter(fish -> usersIds.contains(fish.getUser().getId()))
                .filter(fish -> fish.getLat().add(range).compareTo(fish.getLat()) <= 0 && fish.getLng().add(range).compareTo(range) <= 0)
                .collect(Collectors.toList());
    }
}
