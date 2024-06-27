package com.example.collab_writing.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@NoArgsConstructor
@Data
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
}