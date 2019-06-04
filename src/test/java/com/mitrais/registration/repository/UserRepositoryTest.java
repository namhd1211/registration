package com.mitrais.registration.repository;

import com.mitrais.registration.constant.DataTest;
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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository users;

    private final User user = new User(DataTest.FIRST_NAME, DataTest.LAST_NAME, DataTest.PHONE, DataTest.EMAIL);

    @Before
    public void fillSomeDataIntoOurDb() {
        // Add new Users to Database
        entityManager.persist(user);
    }

    @Test
    public void testExistsByEmail() {
        boolean existsUser = users.existsByEmail("nam@gmail.com");
        Assert.assertTrue(existsUser);
    }

    @Test
    public void testExistsByPhone() {
        boolean existsUser = users.existsByPhoneNumber("0399998826");
        Assert.assertTrue(existsUser);
    }
}
