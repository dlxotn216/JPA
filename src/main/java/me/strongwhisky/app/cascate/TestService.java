package me.strongwhisky.app.cascate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by taesu on 2018-04-27.
 */
@Service
public class TestService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void addUserAndUserRoles(){
        Role role1 = new Role();
        role1.setRoleName("ADMIN");
        roleRepository.save(role1);

        User user1 = new User();
        user1.setUserId("test1@tse.com");
        user1.setUserName("test1");
        user1.addUserRole(role1);

        User user2 = new User();
        user2.setUserId("test2@tse.com");
        user2.setUserName("test2");
        user2.addUserRole(role1);

        userRepository.saveAll(Arrays.asList(user1, user2));
        printAll();
    }

    @Transactional
    public void addRoleAndUserUsers(){
        User user3 = new User();
        user3.setUserId("test3@tse.com");
        user3.setUserName("test3");
        userRepository.save(user3);

        Role role3 = new Role();
        role3.setRoleName("USER");
        role3.addUserRole(user3);

        Role role4 = new Role();
        role4.setRoleName("CRA");
        role4.addUserRole(user3);

        roleRepository.saveAll(Arrays.asList(role3, role4));
        printAll();
    }

    //Role도 저장하지 않고 값만 셋티앟고 사용자만 저장
    @Transactional
    public void addUsers(){
        User user4 = new User();
        user4.setUserId("test4@tse.com");
        user4.setUserName("test4");

        User user5 = new User();
        user5.setUserId("test5@tse.com");
        user5.setUserName("test5");

        Role role5 = new Role();
        role5.setRoleName("PM");
        role5.addUserRole(user4);
        role5.addUserRole(user5);

        Role role6 = new Role();
        role6.setRoleName("CTA");
        role6.addUserRole(user4);

        userRepository.saveAll(Arrays.asList(user4, user5));
        printAll();
    }

    @Transactional(readOnly = true)
    public void printAll(){
        userRepository.findAll()
                .forEach(user->{
                    user.getUserRoles().forEach(userRole->{
                        System.out.println(user.getUserId()+"'s role"+userRole.getRole().getRoleName());
                    });
                });
    }

}
