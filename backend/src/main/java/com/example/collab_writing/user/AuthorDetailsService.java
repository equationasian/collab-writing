package com.example.collab_writing.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthorDetailsService implements UserDetailsService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Author> author = authorRepository.findByUsername(username);
        return author.map(AuthorDetails::new).orElseThrow();
    }

    public Author getAuthor(String username) {
        Optional<Author> author = authorRepository.findByUsername(username);
        return author.orElseThrow();
    }

    public void registerUser(Author author) {
        if (authorRepository.exists(Example.of(author))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(author.getPassword());
        author.setPassword(encodedPassword);
        authorRepository.save(author);
    }

    public void deleteUser(Author author) {
        authorRepository.delete(author);
    }
}
