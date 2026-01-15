package com.cinemahub.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinemahub.backend.model.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
