package com.example.collab_writing.story;

import com.example.collab_writing.user.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class StoryRequest {
    private String title;
    private Set<Author> contributors = new HashSet<>();
    private String viewingPrivacySetting;
    private String contributionPrivacySetting;
    private String contributionMode;
    private String storyBody;
}
