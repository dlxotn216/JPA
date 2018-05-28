package me.strongwhisky.app.day26.domain.repository;

import me.strongwhisky.app.day26.domain.model.User;
import me.strongwhisky.app.day26.domain.model.UserForDropdown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by taesu on 2018-05-28.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class UserProjectionRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProjectionRepository userProjectionRepository;

    @Test
    public void findAllByNameTest() {
        //Given
        userRepository.saveAll(Arrays.asList(
                getUser("test1", "test11", 13, "test@test.com"),
                getUser("test2", "test2", 23, "test@test.com"),
                getUser("test3", "test3", 33, "test@test.com"),
                getUser("test4", "test4", 43, "test@test.com")
        ));

        //When, Then
        userProjectionRepository.findAllByNameIsLike("%test%")
                .forEach(user -> {
                    assertThat(user.getName()).isNotNull();
                    assertThat(user.getUserKey()).isNotNull();
                });

        //Log
        userProjectionRepository.findAllByNameIsLike("%test%")
                .forEach(user -> {
                    System.out.println("[" + user.getUserKey() + "] " + user.getName());
                });
    }

    @Test
    public void findAllByEmailIsLikeTest(){
        //Given
        userRepository.saveAll(Arrays.asList(
                getUser("test1", "test11", 13, "test@test.com"),
                getUser("test2", "test2", 23, "test@test.com"),
                getUser("test3", "test3", 33, "test@test.com"),
                getUser("test4", "test4", 43, "test@test.com")
        ));

        //When, Then
        userProjectionRepository.findAllByEmailIsLike("%est%")
                .forEach(user -> {
                    assertThat(user.getName()).isNotNull();
                    assertThat(user.getUserKey()).isNotNull();
                });

        //Log
        userProjectionRepository.findAllByEmailIsLike("%est%")
                .forEach(user -> {
                    System.out.println("[" + user.getUserKey() + "] " + user.getName());
                });
    }

    @Test
    public void findAllByAgeBetweenTest(){
        //Given
        userRepository.saveAll(Arrays.asList(
                getUser("test1", "test11", 13, "test@test.com"),
                getUser("test2", "test2", 23, "test@test.com"),
                getUser("test3", "test3", 33, "test@test.com"),
                getUser("test4", "test4", 43, "test@test.com")
        ));

        //When, Then
        userProjectionRepository.findAllByAgeBetween(19, 99, UserForDropdown.class)
                .forEach(user -> {
                    assertThat(user.getName()).isNotNull();
                    assertThat(user.getUserKey()).isNotNull();
                });

        //Log
        userProjectionRepository.findAllByAgeBetween(19, 99, UserForDropdown.class)
                .forEach(user -> {
                    System.out.println("[" + user.getUserKey() + "] " + user.getName());
                });
    }

    private User getUser(String name, String id, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setUserId(id);
        user.setAge(age);
        user.setEmail(email);
        return user;
    }

}