package com.example.fishapp.controller;

import com.example.fishapp.dto.FishCreateDto;
import com.example.fishapp.model.Fish;
import com.example.fishapp.model.User;
import com.example.fishapp.repository.FishRepository;
import com.example.fishapp.service.FishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    @PostMapping(consumes = {"multipart/form-data"})
    public Fish create(
            @RequestAttribute @NotNull User user,
            @ModelAttribute @Valid FishCreateDto fishCreateDto) throws Exception {
        return fishService.createFish(fishCreateDto, user);
    }

    @GetMapping("/my")
    private List<Fish> get(@RequestAttribute @NotNull User user) {
        return fishRepository.findByUser(user);
    }

}
