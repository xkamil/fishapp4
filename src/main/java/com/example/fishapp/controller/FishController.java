package com.example.fishapp.controller;

import com.example.fishapp.dto.FishCreateDto;
import com.example.fishapp.model.Fish;
import com.example.fishapp.service.FishService;
import com.example.fishapp.repository.FishRepository;
import com.example.fishapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fishes")
public class FishController {
    private static final Logger log = LoggerFactory.getLogger(FishController.class);

    private final FishRepository fishRepository;
    private final FishService fishService;

    public FishController(FishRepository fishRepository, FishService fishService) {
        this.fishRepository = fishRepository;
        this.fishService = fishService;
    }

    @PostMapping
    private Fish create(@RequestAttribute @NotNull User user, @RequestBody @Valid FishCreateDto fishCreateDto) {
        return fishService.createFish(fishCreateDto, user);
    }

    @GetMapping("/my")
    private List<Fish> get(@RequestAttribute @NotNull User user) {
        return fishRepository.findByUser(user);
    }

    @GetMapping
    private List<Fish> getInRange(@RequestParam List<UUID> userIds, @RequestParam BigDecimal range) {
        return fishService.findFishes(userIds, range);
    }
}
