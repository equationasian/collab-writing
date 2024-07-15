package com.example.collab_writing.story;

import com.example.collab_writing.user.Author;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stories")
@NoArgsConstructor
@Data
public class Story {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @OneToOne
    private Author author;
    @OneToMany(fetch = FetchType.LAZY)
    @ElementCollection
    @CollectionTable(name = "contributors", joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Set<Author> contributors = new HashSet<>();
    private String viewingPrivacySetting;
    private String contributionPrivacySetting;
    private String contributionMode;
    private String storyBody;
}