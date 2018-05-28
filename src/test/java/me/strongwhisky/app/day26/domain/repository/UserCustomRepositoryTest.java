package me.strongwhisky.app.day26.domain.repository;

import me.strongwhisky.app.day26.domain.model.Group;
import me.strongwhisky.app.day26.domain.model.User;
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
public class UserCustomRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void findAllByAgeGreaterThanWithAllGraphTest() {
        //Given
        Group group1 = getGroup("Group1", "teawt");
        group1.addUser(getUser("test1", "test11", 13, "test@test.com"));
        group1.addUser(getUser("test2", "test2", 23, "test@test.com"));
        group1.addUser(getUser("test3", "test3", 33, "test@test.com"));

        Group group2 = getGroup("Group2", "Greupae2");
        group2.addUser(getUser("test4", "test4", 43, "test@test.com"));

        groupRepository.saveAll(Arrays.asList(group1, group2));

        //When, Then
        userRepository.findAllByAgeGreaterThanWithAllGraph(13)
                .forEach(user -> {
                    assertThat(user.getName()).isNotNull();
                    assertThat(user.getUserKey()).isNotNull();
                });

        //Log
        userRepository.findAllByAgeGreaterThanWithAllGraph(13)
                .forEach(user -> {
                    System.out.println("User group is :" + user.getGroup().getGroupName());
                    System.out.println("[" + user.getUserKey() + "] " + user.getName() + "::" + user.getUserId());
                });
    }

    private Group getGroup(String groupName, String description){
        Group group = new Group();
        group.setGroupName(groupName);
        group.setDescription(description);
        return group;
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