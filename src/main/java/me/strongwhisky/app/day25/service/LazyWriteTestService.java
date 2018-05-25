package me.strongwhisky.app.day25.service;

import me.strongwhisky.app.day25.domain.model.Group;
import me.strongwhisky.app.day25.domain.model.User;
import me.strongwhisky.app.day25.domain.repository.GroupRepository;
import me.strongwhisky.app.day25.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-05-26.
 */
@Service
public class LazyWriteTestService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * =================================writeByEntityManager=================================
     * write by writeByEntityManager User[7]
     * write by writeByEntityManager User[8]
     * write by writeByEntityManager User[9]
     * write by writeByEntityManager User[10]
     * write by writeByEntityManager User[11]
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * end of writeByEntityManager
     */
    public void writeByEntityManager() {
        System.out.println("=================================writeByEntityManager=================================");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setName("User00" + i);
            user.setAge(23);
            user.setUserId("user00" + i + "@test.com");
            user.setEmail("test@test.com");

            entityManager.persist(user);
            System.out.println("write by writeByEntityManager User[" + user.getUserKey() + "]");
        }

        entityTransaction.commit();
        System.out.println("end of writeByEntityManager");
    }

    /**
     * =================================writeByRepository=================================
     * write by writeByRepository User[12]
     * write by writeByRepository User[13]
     * write by writeByRepository User[14]
     * write by writeByRepository User[15]
     * write by writeByRepository User[16]
     * end of writeByRepository
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     */
    @Transactional
    public void writeByRepository() {
        System.out.println("=================================writeByRepository=================================");
        for (int i = 6; i <= 10; i++) {
            User user = new User();
            user.setName("User00" + i);
            user.setAge(23);
            user.setUserId("user00" + i + "@test.com");
            user.setEmail("test@test.com");

            userRepository.save(user);
            System.out.println("write by writeByRepository User[" + user.getUserKey() + "]");
        }
        System.out.println("end of writeByRepository");
    }

    /**
     * =================================writeUserAndGroupByEntityManger=================================
     * write by writeUserAndGroupByEntityManger User[17]
     * write by writeUserAndGroupByEntityManger Group[17]
     * write by writeUserAndGroupByEntityManger User[18]
     * write by writeUserAndGroupByEntityManger Group[18]
     * write by writeUserAndGroupByEntityManger User[19]
     * write by writeUserAndGroupByEntityManger Group[19]
     * write by writeUserAndGroupByEntityManger User[20]
     * write by writeUserAndGroupByEntityManger Group[20]
     * write by writeUserAndGroupByEntityManger User[21]
     * write by writeUserAndGroupByEntityManger Group[21]
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * end of writeUserAndGroupByEntityManger
     */
    public void writeUserAndGroupByEntityManger() {
        System.out.println("=================================writeUserAndGroupByEntityManger=================================");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        for (int i = 11; i <= 15; i++) {
            User user = new User();
            user.setName("User00" + i);
            user.setAge(23);
            user.setUserId("user00" + i + "@test.com");
            user.setEmail("test@test.com");

            entityManager.persist(user);
            System.out.println("write by writeUserAndGroupByEntityManger User[" + user.getUserKey() + "]");

            Group group = new Group();
            group.setGroupName("Group00" + i);
            group.setDescription("testGroup+" + i);
            entityManager.persist(group);

            System.out.println("write by writeUserAndGroupByEntityManger Group[" + user.getUserKey() + "]");
        }

        entityTransaction.commit();
        System.out.println("end of writeUserAndGroupByEntityManger");
    }

    /**
     * =================================writeUserAndGroupByRepository=================================
     * write by writeUserAndGroupByRepository User[22]
     * write by writeUserAndGroupByRepository Group[22]
     * write by writeUserAndGroupByRepository User[23]
     * write by writeUserAndGroupByRepository Group[23]
     * write by writeUserAndGroupByRepository User[24]
     * write by writeUserAndGroupByRepository Group[24]
     * write by writeUserAndGroupByRepository User[25]
     * write by writeUserAndGroupByRepository Group[25]
     * write by writeUserAndGroupByRepository User[26]
     * write by writeUserAndGroupByRepository Group[26]
     * end of writeUserAndGroupByRepository
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     * insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     * insert into app_group (description, group_name, group_key) values (?, ?, ?)
     */
    @Transactional
    public void writeUserAndGroupByRepository() {
        System.out.println("=================================writeUserAndGroupByRepository=================================");
        for (int i = 16; i <= 20; i++) {
            User user = new User();
            user.setName("User00" + i);
            user.setAge(23);
            user.setUserId("user00" + i + "@test.com");
            user.setEmail("test@test.com");

            userRepository.save(user);
            System.out.println("write by writeUserAndGroupByRepository User[" + user.getUserKey() + "]");

            Group group = new Group();
            group.setGroupName("Group00" + i);
            group.setDescription("testGroup+" + i);
            groupRepository.save(group);
            System.out.println("write by writeUserAndGroupByRepository Group[" + user.getUserKey() + "]");
        }
        System.out.println("end of writeUserAndGroupByRepository");
    }

    /**
     =================================writeUserAndGroupBySaveAll=================================
     write by writeUserAndGroupBySaveAll User[null]
     write by writeUserAndGroupBySaveAll Group[null]
     write by writeUserAndGroupBySaveAll User[null]
     write by writeUserAndGroupBySaveAll Group[null]
     write by writeUserAndGroupBySaveAll User[null]
     write by writeUserAndGroupBySaveAll Group[null]
     write by writeUserAndGroupBySaveAll User[null]
     write by writeUserAndGroupBySaveAll Group[null]
     write by writeUserAndGroupBySaveAll User[null]
     write by writeUserAndGroupBySaveAll Group[null]
     end of writeUserAndGroupByRepository
     insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     insert into app_user (age, email, group_key, name, user_id, user_key) values (?, ?, ?, ?, ?, ?)
     insert into app_group (description, group_name, group_key) values (?, ?, ?)
     insert into app_group (description, group_name, group_key) values (?, ?, ?)
     insert into app_group (description, group_name, group_key) values (?, ?, ?)
     insert into app_group (description, group_name, group_key) values (?, ?, ?)
     insert into app_group (description, group_name, group_key) values (?, ?, ?)
     */
    @Transactional
    public void writeUserAndGroupBySaveAll() {
        System.out.println("=================================writeUserAndGroupBySaveAll=================================");
        List<User> users = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        for (int i = 21; i <= 25; i++) {
            User user = new User();
            user.setName("User00" + i);
            user.setAge(23);
            user.setUserId("user00" + i + "@test.com");
            user.setEmail("test@test.com");

            users.add(user);
            System.out.println("write by writeUserAndGroupBySaveAll User[" + user.getUserKey() + "]");

            Group group = new Group();
            group.setGroupName("Group00" + i);
            group.setDescription("testGroup+" + i);
            groups.add(group);
            System.out.println("write by writeUserAndGroupBySaveAll Group[" + user.getUserKey() + "]");
        }

        userRepository.saveAll(users);
        groupRepository.saveAll(groups);
        System.out.println("end of writeUserAndGroupByRepository");
    }
}
