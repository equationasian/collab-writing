package com.example.collab_writing.register;

import com.example.collab_writing.user.Author;
import com.example.collab_writing.user.AuthorDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegisterService {
    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private AuthorDetailsService authorDetailsService;

    public boolean register(RegisterAuthor author) {
        Author newAuthor = new Author();
        newAuthor.setUsername(author.username());
        newAuthor.setPassword(author.password());
        newAuthor.setEmail(author.email());

        try {
            authorDetailsService.registerUser(newAuthor);
        }
        catch (ResponseStatusException e) {
            log.warn("Username already exists", e);
            return false;
        }

        return true;
    }
}