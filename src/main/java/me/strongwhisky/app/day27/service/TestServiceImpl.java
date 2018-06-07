package me.strongwhisky.app.day27.service;

import me.strongwhisky.app.day27.domain.model.Group;
import me.strongwhisky.app.day27.domain.model.User;
import me.strongwhisky.app.day27.domain.repository.GroupRepository;
import me.strongwhisky.app.day27.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taesu on 2018-05-24.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    /*
    ===========================testForNotSaveCall_WhenNotChangeGroup====================
    2018-06-07 21:23:27.506 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
    2018-06-07 21:23:27.550 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
    2018-06-07 21:23:27.570 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
    2018-06-07 21:23:27.571 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
    2018-06-07 21:23:27.575 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
    2018-06-07 21:23:27.575 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
    2018-06-07 21:23:27.576 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
    2018-06-07 21:23:27.576 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
    2018-06-07 21:23:27.576 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)

     */

    @Override
    @Transactional
    public void testForNotSaveCall_WhenNotChangeGroup() {
        System.out.println("===========================testForNotSaveCall_WhenNotChangeGroup====================");
        Group group = groupRepository.findById(1L).orElseThrow(IllegalAccessError::new);

        User user = User.createUser("test1", 23);
        User user1 = User.createUser("test2", 12);
        User user2 = User.createUser("test3", 51);

        group.addUser(user);
        group.addUser(user1);
        group.addUser(user2);

        Group group1 = groupRepository.findById(2L).orElseThrow(IllegalAccessError::new);
        User user3 = User.createUser("test4", 42);
        User user4 = User.createUser("test5", 33);
        group1.addUser(user3);
        group1.addUser(user4);
    }

    /*
   ===========================testForNotSaveCall_WhenChangeGroup====================
   2018-06-07 21:23:27.578 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
   2018-06-07 21:23:27.579 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
   2018-06-07 21:23:27.581 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
   2018-06-07 21:23:27.582 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
   2018-06-07 21:23:27.588 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
   2018-06-07 21:23:27.588 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
   2018-06-07 21:23:27.588 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
   2018-06-07 21:23:27.589 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
   2018-06-07 21:23:27.589 DEBUG 1608 --- [  restartedMain] org.hibernate.SQL                        : insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
    */
    @Override
    @Transactional
    public void testForNotSaveCall_WhenChangeGroup() {
        System.out.println("===========================testForNotSaveCall_WhenChangeGroup====================");
        Group group = groupRepository.findById(1L).orElseThrow(IllegalAccessError::new);
        group.setDescription("changed");

        User user = User.createUser("test11", 23);
        User user1 = User.createUser("test12", 12);
        User user2 = User.createUser("test13", 51);

        group.addUser(user);
        group.addUser(user1);
        group.addUser(user2);

        Group group1 = groupRepository.findById(2L).orElseThrow(IllegalAccessError::new);
        User user3 = User.createUser("test14", 42);
        User user4 = User.createUser("test15", 33);
        group1.addUser(user3);
        group1.addUser(user4);
    }

    /*
    ================================changeUsersTest+==================================
    2018-06-07 21:58:56.803 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
    2018-06-07 21:58:56.803 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
    2018-06-07 21:58:56.828  INFO 1204 --- [  restartedMain] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
    2018-06-07 21:58:56.957 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_2_, user0_.age as age2_2_, user0_.email as email3_2_, user0_.group_key as group_ke6_2_, user0_.name as name4_2_, user0_.user_id as user_id5_2_ from app_user user0_ where user0_.user_key in (? , ? , ?)
     */
    @Override
    @Transactional
    public void changeUsersTest(){
        System.out.println("================================changeUsersTest+==================================");
        Group group = groupRepository.findById(1L).orElseThrow(IllegalAccessError::new);

        List<User> users = userRepository.findAllById(Arrays.asList(4L, 5L, 6L));
        group.changeUsers(users);

    }

    /*
    ================================clearAndChangeUsersTest+==================================
    2018-06-07 21:58:56.965 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
    2018-06-07 21:58:56.966 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
    2018-06-07 21:58:56.968 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_2_, user0_.age as age2_2_, user0_.email as email3_2_, user0_.group_key as group_ke6_2_, user0_.name as name4_2_, user0_.user_id as user_id5_2_ from app_user user0_ where user0_.user_key in (? , ? , ?)
     */
    @Override
    @Transactional
    public void clearAndChangeUsersTest(){
        System.out.println("================================clearAndChangeUsersTest+==================================");
        Group group = groupRepository.findById(2L).orElseThrow(IllegalAccessError::new);

        List<User> users = userRepository.findAllById(Arrays.asList(1L, 2L, 3L));
        group.claerUsersAndSetUsers(users);
    }

    /*
    ================================changeBulk+==================================
    2018-06-07 21:58:56.969 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
    2018-06-07 21:58:56.970 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
    2018-06-07 21:58:56.983 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_2_, user0_.age as age2_2_, user0_.email as email3_2_, user0_.group_key as group_ke6_2_, user0_.name as name4_2_, user0_.user_id as user_id5_2_ from app_user user0_ left outer join app_group group1_ on user0_.group_key=group1_.group_key where group1_.group_key=?
    2018-06-07 21:58:58.754 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_2_, user0_.age as age2_2_, user0_.email as email3_2_, user0_.group_key as group_ke6_2_, user0_.name as name4_2_, user0_.user_id as user_id5_2_ from app_user user0_ where user0_.user_key in (? , ? , ?)
    2018-06-07 21:59:04.263 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
    2018-06-07 21:59:04.268 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
    2018-06-07 21:59:05.532 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.534 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.535 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.536 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.537 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.538 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.539 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.540 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.540 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
    2018-06-07 21:59:05.541 DEBUG 1204 --- [  restartedMain] org.hibernate.SQL                        : update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
     */
    @Override
    @Transactional
    public void changeBulk(){
        System.out.println("================================changeBulk+==================================");
        Group group = groupRepository.findById(2L).orElseThrow(IllegalAccessError::new);

        List<User> users = userRepository.findAllById(Arrays.asList(1L, 2L, 3L));
        group.changeBulk(users);
    }
}
