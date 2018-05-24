package me.strongwhisky.app.day24.service;

import me.strongwhisky.app.day24.domain.model.Group;
import me.strongwhisky.app.day24.domain.model.User;
import me.strongwhisky.app.day24.domain.repository.GroupRepository;
import me.strongwhisky.app.day24.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by taesu on 2018-05-24.
 */
@Service
public class TestService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void testFindOneUser(){
        System.out.println("========================testFindOneUser======================");
        User user = userRepository.findById(1L).get();
        System.out.println(user.getUserKey() + "::" + user.getUserId());
        System.out.println(user.getName() + "'s group is :" + user.getGroup().getGroupName());

        /*
            select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_, group1_.group_key as group_ke1_1_1_, group1_.description as descript2_1_1_, group1_.group_name as group_na3_1_1_ from app_user user0_ left outer join app_group group1_ on user0_.group_key=group1_.group_key where user0_.user_key=?
            select users0_.group_key as group_ke6_2_1_, users0_.user_key as user_key1_2_1_, users0_.user_key as user_key1_2_0_, users0_.age as age2_2_0_, users0_.email as email3_2_0_, users0_.group_key as group_ke6_2_0_, users0_.name as name4_2_0_, users0_.user_id as user_id5_2_0_ from app_user users0_ where users0_.group_key=?
         */

         /*
        @Batch 옵션을 줄 경우
            select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_, group1_.group_key as group_ke1_1_1_, group1_.description as descript2_1_1_, group1_.group_name as group_na3_1_1_ from app_user user0_ left outer join app_group group1_ on user0_.group_key=group1_.group_key where user0_.user_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
         */

         /*
        @Fetch 옵션 줄 경우
            select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_, group1_.group_key as group_ke1_1_1_, group1_.description as descript2_1_1_, group1_.group_name as group_na3_1_1_ from app_user user0_ left outer join app_group group1_ on user0_.group_key=group1_.group_key where user0_.user_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
        */
    }

    public void testFindOneUserByEntityManager(){
        System.out.println("========================testFindOneUserByEntityManager======================");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, 1L);
        System.out.println(user.getUserKey() + "::" + user.getUserId());
        System.out.println(user.getName() + "'s group is :" + user.getGroup().getGroupName());

        entityTransaction.commit();
        /*
            select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_, group1_.group_key as group_ke1_1_1_, group1_.description as descript2_1_1_, group1_.group_name as group_na3_1_1_ from app_user user0_ left outer join app_group group1_ on user0_.group_key=group1_.group_key where user0_.user_key=?
            select users0_.group_key as group_ke6_2_1_, users0_.user_key as user_key1_2_1_, users0_.user_key as user_key1_2_0_, users0_.age as age2_2_0_, users0_.email as email3_2_0_, users0_.group_key as group_ke6_2_0_, users0_.name as name4_2_0_, users0_.user_id as user_id5_2_0_ from app_user users0_ where users0_.group_key=?
         */

         /*
        @Batch 옵션을 줄 경우
            select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_, group1_.group_key as group_ke1_1_1_, group1_.description as descript2_1_1_, group1_.group_name as group_na3_1_1_ from app_user user0_ left outer join app_group group1_ on user0_.group_key=group1_.group_key where user0_.user_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
         */

         /*
        @Fetch 옵션 줄 경우
            select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_, group1_.group_key as group_ke1_1_1_, group1_.description as descript2_1_1_, group1_.group_name as group_na3_1_1_ from app_user user0_ left outer join app_group group1_ on user0_.group_key=group1_.group_key where user0_.user_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
        */
    }

    public void testFindAllUsers() {
        System.out.println("========================testFindAllUsers======================");
        userRepository.findAll()
                .forEach(user -> {
                    System.out.println(user.getUserKey() + "::" + user.getUserId());

                    System.out.println(user.getName() + "'s group is :" + user.getGroup().getGroupName());
                });

        /*
        N+1
            select user0_.user_key as user_key1_2_, user0_.age as age2_2_, user0_.email as email3_2_, user0_.group_key as group_ke6_2_, user0_.name as name4_2_, user0_.user_id as user_id5_2_ from app_user user0_
            select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_, users1_.group_key as group_ke6_2_1_, users1_.user_key as user_key1_2_1_, users1_.user_key as user_key1_2_2_, users1_.age as age2_2_2_, users1_.email as email3_2_2_, users1_.group_key as group_ke6_2_2_, users1_.name as name4_2_2_, users1_.user_id as user_id5_2_2_ from app_group group0_ left outer join app_user users1_ on group0_.group_key=users1_.group_key where group0_.group_key=?
            select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_, users1_.group_key as group_ke6_2_1_, users1_.user_key as user_key1_2_1_, users1_.user_key as user_key1_2_2_, users1_.age as age2_2_2_, users1_.email as email3_2_2_, users1_.group_key as group_ke6_2_2_, users1_.name as name4_2_2_, users1_.user_id as user_id5_2_2_ from app_group group0_ left outer join app_user users1_ on group0_.group_key=users1_.group_key where group0_.group_key=?
         */

        /*
        @Batch 옵션을 줄 경우
            select user0_.user_key as user_key1_2_, user0_.age as age2_2_, user0_.email as email3_2_, user0_.group_key as group_ke6_2_, user0_.name as name4_2_, user0_.user_id as user_id5_2_ from app_user user0_
            select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_, users1_.group_key as group_ke6_2_1_, users1_.user_key as user_key1_2_1_, users1_.user_key as user_key1_2_2_, users1_.age as age2_2_2_, users1_.email as email3_2_2_, users1_.group_key as group_ke6_2_2_, users1_.name as name4_2_2_, users1_.user_id as user_id5_2_2_ from app_group group0_ left outer join app_user users1_ on group0_.group_key=users1_.group_key where group0_.group_key=?
            select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_, users1_.group_key as group_ke6_2_1_, users1_.user_key as user_key1_2_1_, users1_.user_key as user_key1_2_2_, users1_.age as age2_2_2_, users1_.email as email3_2_2_, users1_.group_key as group_ke6_2_2_, users1_.name as name4_2_2_, users1_.user_id as user_id5_2_2_ from app_group group0_ left outer join app_user users1_ on group0_.group_key=users1_.group_key where group0_.group_key=?
         */

        /*
        @Fetch 옵션 줄 경우
            select user0_.user_key as user_key1_2_, user0_.age as age2_2_, user0_.email as email3_2_, user0_.group_key as group_ke6_2_, user0_.name as name4_2_, user0_.user_id as user_id5_2_ from app_user user0_
            select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
            select group0_.group_key as group_ke1_1_0_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_ from app_group group0_ where group0_.group_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?

         */
    }

    public void testFindAllGroups(){
        System.out.println("========================testFindAllGroups======================");
        //Group entity에서 users fetch type을 EAGER로 줄 경우
        groupRepository.findAll()
                .forEach(group -> {
                    System.out.println(group.getGroupKey()+"::"+group.getGroupName());

                    group.getUsers().forEach(user -> {
                        System.out.println(user.getUserKey() + "::" + user.getUserId());
                    });
                });

        /*
        N+!
            select group0_.group_key as group_ke1_1_, group0_.description as descript2_1_, group0_.group_name as group_na3_1_ from app_group group0_
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
         */

        /*
        Group Entity에 BatchSize option을 줄 경우
            select group0_.group_key as group_ke1_1_, group0_.description as descript2_1_, group0_.group_name as group_na3_1_ from app_group group0_
            select users0_.group_key as group_ke6_2_1_, users0_.user_key as user_key1_2_1_, users0_.user_key as user_key1_2_0_, users0_.age as age2_2_0_, users0_.email as email3_2_0_, users0_.group_key as group_ke6_2_0_, users0_.name as name4_2_0_, users0_.user_id as user_id5_2_0_ from app_user users0_ where users0_.group_key in (?, ?, ?)
         */

        /*
        @Fetch 옵션 줄 경우
            select group0_.group_key as group_ke1_1_, group0_.description as descript2_1_, group0_.group_name as group_na3_1_ from app_group group0_
            select users0_.group_key as group_ke6_2_1_, users0_.user_key as user_key1_2_1_, users0_.user_key as user_key1_2_0_, users0_.age as age2_2_0_, users0_.email as email3_2_0_, users0_.group_key as group_ke6_2_0_, users0_.name as name4_2_0_, users0_.user_id as user_id5_2_0_ from app_user users0_ where users0_.group_key in (select group0_.group_key from app_group group0_)
         */
    }

    public void testFindAllGroupsByEntityManager(){
        System.out.println("========================testFindAllGroupsByEntityManager======================");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.createQuery("select g from Group g", Group.class)
                .getResultList()
                .forEach(group -> {
                    System.out.println(group.getGroupKey()+"::"+group.getGroupName());

                    group.getUsers().forEach(user -> {
                        System.out.println(user.getUserKey() + "::" + user.getUserId());
                    });
                });

        entityTransaction.commit();
        /*
        N+!
            select group0_.group_key as group_ke1_1_, group0_.description as descript2_1_, group0_.group_name as group_na3_1_ from app_group group0_
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
            select users0_.group_key as group_ke6_2_0_, users0_.user_key as user_key1_2_0_, users0_.user_key as user_key1_2_1_, users0_.age as age2_2_1_, users0_.email as email3_2_1_, users0_.group_key as group_ke6_2_1_, users0_.name as name4_2_1_, users0_.user_id as user_id5_2_1_ from app_user users0_ where users0_.group_key=?
         */

          /*
        Group Entity에 BatchSize option을 줄 경우
            select group0_.group_key as group_ke1_1_, group0_.description as descript2_1_, group0_.group_name as group_na3_1_ from app_group group0_
            select users0_.group_key as group_ke6_2_1_, users0_.user_key as user_key1_2_1_, users0_.user_key as user_key1_2_0_, users0_.age as age2_2_0_, users0_.email as email3_2_0_, users0_.group_key as group_ke6_2_0_, users0_.name as name4_2_0_, users0_.user_id as user_id5_2_0_ from app_user users0_ where users0_.group_key in (?, ?, ?)
         */

        /*
        @Fetch 옵션 줄 경우
            select group0_.group_key as group_ke1_1_, group0_.description as descript2_1_, group0_.group_name as group_na3_1_ from app_group group0_
            select users0_.group_key as group_ke6_2_1_, users0_.user_key as user_key1_2_1_, users0_.user_key as user_key1_2_0_, users0_.age as age2_2_0_, users0_.email as email3_2_0_, users0_.group_key as group_ke6_2_0_, users0_.name as name4_2_0_, users0_.user_id as user_id5_2_0_ from app_user users0_ where users0_.group_key in (select group0_.group_key from app_group group0_)
         */
    }
}
