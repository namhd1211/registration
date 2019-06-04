package com.mitrais.registration.repository;

import com.mitrais.registration.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository users;

    private User user = User.builder().firstName("Nam").lastName("Tran").email("trannam@gmail.com").phoneNumber("0399998826").build();
    LocalDate dob = LocalDate.parse("2019-05-28");
    private User user1 = User.builder().firstName("Hung").lastName("Tran").email("hung@gmail.com").phoneNumber("0399998827").dob(dob).build();

    @Before
    public void fillSomeDataIntoOurDb() {
        // Add new Users to Database
        entityManager.persist(user);
        entityManager.persist(user1);
    }
    @Test
    public void testExistsByEmail() {
        boolean existsUser = users.existsByEmail("trannam@gmail.com");
        Assert.assertTrue(existsUser);
    }

    @Test
    public void testExistsByPhone() {
        boolean existsUser = users.existsByPhoneNumber("0399998826");
        Assert.assertTrue(existsUser);
    }
}
