package com.example.collab_writing.story;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
public class StoryController {
    private static final Logger log = LoggerFactory.getLogger(StoryController.class);

    @Autowired
    private StoryService storyService;

    @GetMapping("/all")
    public List<Story> getStories() {
        return storyService.getStories();
    }

    @GetMapping("/find")
    public ResponseEntity<Story> findStory(@RequestBody StoryRequest request) {
        return ResponseEntity.ok(storyService.getStory(request.getTitle()));
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addStory(@RequestBody StoryRequest request) {
        boolean isSaved = storyService.addStory(request);
        if (isSaved) {
            return ResponseEntity.ok(isSaved);
        }

        return ResponseEntity.badRequest().body(isSaved);
    }
}