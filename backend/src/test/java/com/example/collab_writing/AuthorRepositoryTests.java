package com.example.collab_writing;

import com.example.collab_writing.user.Author;
import com.example.collab_writing.user.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class AuthorRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void createAuthorTest() {
        Author author = new Author();
        author.setUsername("Test");
        author.setPassword("password");
        author.setEmail("test@gmail.com");

        Author savedAuthor = authorRepository.save(author);
        Author existAuthor = entityManager.find(Author.class, savedAuthor.getId());

        assertThat(author.getEmail()).isEqualTo(existAuthor.getEmail());
    }
}