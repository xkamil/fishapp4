package com.example.fishapp.repository;

import com.example.fishapp.model.Fish;
import com.example.fishapp.app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FishRepository extends JpaRepository<Fish, UUID> {

    List<Fish> findByUser(User user);
}
