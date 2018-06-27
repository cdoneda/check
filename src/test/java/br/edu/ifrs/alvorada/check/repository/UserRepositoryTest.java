package br.edu.ifrs.alvorada.check.repository;

import br.edu.ifrs.alvorada.check.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository repository;

    private final String TEST_STRING = "Test String";

    @Test
    public void when_FindByUsername_then_ReturnOptional(){

        // given
        User user = new User();
        user.setUsername(TEST_STRING);
        user.setName(TEST_STRING);
        user.setEmail("email@email.com");
        entityManager.persist(user);
        entityManager.flush();

        // when
        Optional<User> found = repository.findByUsername(TEST_STRING);

        // then
        assertThat(found.get().getUsername()).isEqualTo(TEST_STRING);
    }

    @Test
    public void given_noData_when_FindByUsername_then_ReturnEmptyOptional(){

        // given

        // when
        Optional<User> found = repository.findByUsername(TEST_STRING);

        // then
        assertThat(found).isEmpty();
    }

}