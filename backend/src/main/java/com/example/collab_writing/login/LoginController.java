package com.example.collab_writing.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<Boolean> verifyAuthor(@RequestBody LoginAuthor author) {
        return ResponseEntity.ok(loginService.authenticate(author));
    }
}