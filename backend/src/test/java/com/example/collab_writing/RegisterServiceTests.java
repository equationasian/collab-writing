package com.example.collab_writing;

import com.example.collab_writing.register.RegisterAuthor;
import com.example.collab_writing.register.RegisterService;
import com.example.collab_writing.user.Author;
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
public class RegisterServiceTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RegisterService registerService;

    @Test
    public void registerAuthor() {
        RegisterAuthor author = new RegisterAuthor("test", "test123", "testing@gmail.com");
        registerService.register(author);

        Author registeredAuthor = entityManager.find(Author.class, author.username());

        assertThat(author.username()).isEqualTo(registeredAuthor.getUsername());
    }
}
