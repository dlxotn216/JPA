package me.strongwhisky.app.day27.service;

import me.strongwhisky.app.day27.domain.model.Group;
import me.strongwhisky.app.day27.domain.model.User;
import me.strongwhisky.app.day27.domain.repository.GroupRepository;
import me.strongwhisky.app.day27.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by taesu on 2018-05-24.
 */
@Service
public class InitService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public void init() {
        Group group = new Group();
        group.setGroupName("Group1");
        group.setDescription("그룹 1");

        group.addUser(User.builder().userId("01@test.com").age(13).email("test11@test.com").name("Lee Tae 11111").build());
        group.addUser(User.builder().userId("02@test.com").age(23).email("tes2222t@test.com").name("Lee Tae Su2222").build());
        group.addUser(User.builder().userId("03@test.com").age(33).email("tes3333t@test.com").name("aefeawfawf").build());


        Group group2 = new Group();
        group2.setGroupName("Group2");
        group2.setDescription("그룹 2");

        group2.addUser(User.builder().userId("123@test.com").age(13).email("test11@test.com").name("Lee Tae 11111").build());
        group2.addUser(User.builder().userId("123123@test.com").age(23).email("tes2222t@test.com").name("Lee Tae Su2222").build());
        group2.addUser(User.builder().userId("324124124@test.com").age(33).email("tes3333t@test.com").name("aefeawfawf").build());


        Group group3 = new Group();
        group3.setGroupName("Group3");
        group3.setDescription("그룹 3");
        groupRepository.saveAll(Arrays.asList(group, group2, group3));
    }

}
