package com.example.collab_writing.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByUsername(String username);
}