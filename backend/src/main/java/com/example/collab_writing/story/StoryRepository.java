package com.example.collab_writing.story;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface StoryRepository extends JpaRepository<Story, Long> {
    Optional<Story> findByTitle(String title);
}
