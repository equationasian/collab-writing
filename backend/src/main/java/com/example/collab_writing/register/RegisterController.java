package com.example.collab_writing.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping
    public ResponseEntity<Boolean> registration(@RequestBody RegisterAuthor author) {
        return ResponseEntity.ok(registerService.register(author));
    }
}
