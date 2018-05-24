package me.strongwhisky.app.day24.service;

import me.strongwhisky.app.day24.domain.model.Group;
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
public class SolutionService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public void usingFetchJoin(){
        System.out.println("========================usingFetchJoin======================");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.createQuery("select distinct g from Group g join fetch g.users", Group.class)
                .getResultList()
                .forEach(group -> {
                    System.out.println(group.getGroupKey()+"::"+group.getGroupName());

                    group.getUsers().forEach(user -> {
                        System.out.println(user.getUserKey() + "::" + user.getUserId());
                    });
                });

        /*
            select distinct group0_.group_key as group_ke1_1_0_, users1_.user_key as user_key1_2_1_, group0_.description as descript2_1_0_, group0_.group_name as group_na3_1_0_, users1_.age as age2_2_1_, users1_.email as email3_2_1_, users1_.group_key as group_ke6_2_1_, users1_.name as name4_2_1_, users1_.user_id as user_id5_2_1_, users1_.group_key as group_ke6_2_0__, users1_.user_key as user_key1_2_0__ from app_group group0_ inner join app_user users1_ on group0_.group_key=users1_.group_key
         */

        entityTransaction.commit();
    }
}
