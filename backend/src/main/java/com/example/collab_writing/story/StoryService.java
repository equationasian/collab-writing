package com.example.collab_writing.story;

import com.example.collab_writing.user.Author;
import com.example.collab_writing.user.AuthorDetails;
import com.example.collab_writing.user.AuthorDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StoryService {
    private static final Logger log = LoggerFactory.getLogger(StoryService.class);

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private AuthorDetailsService authorDetailsService;

    public Story getStory(String title) {
        Optional<Story> story = storyRepository.findByTitle(title);
        if (story.isEmpty()) {
            throw new NoSuchElementException("Story does not exist");
        }

        return story.get();
    }

    public boolean addStory(StoryRequest storyRequest) {
        Story story = new Story();
        story.setTitle(storyRequest.getTitle());
        story.setContributors(storyRequest.getContributors());
        story.setViewingPrivacySetting(storyRequest.getViewingPrivacySetting());
        story.setContributionPrivacySetting(storyRequest.getContributionPrivacySetting());
        story.setContributionMode(storyRequest.getContributionMode());
        story.setStoryBody(storyRequest.getStoryBody());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthorDetails authorDetails = (AuthorDetails) auth.getPrincipal();
        Author author = authorDetailsService.getAuthor(authorDetails.getUsername());
        story.setAuthor(author);

        try {
            storyRepository.save(story);
        }
        catch (Exception e) {
            log.warn("Story not saved", e);
            return false;
        }

        return true;
    }

    public List<Story> getStories() {
        return storyRepository.findAll();
    }
}
