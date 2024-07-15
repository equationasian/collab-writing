package com.example.collab_writing.login;

import com.example.collab_writing.user.Author;
import com.example.collab_writing.user.AuthorDetails;
import com.example.collab_writing.user.AuthorDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private AuthorDetailsService authorDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public boolean authenticate(LoginAuthor author) {
        Authentication auth;

        try {
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(author.username(), author.password()));
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }
}
