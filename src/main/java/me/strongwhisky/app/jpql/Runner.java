package me.strongwhisky.app.jpql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by taesu on 2018-05-01.
 */
@Component
public class Runner implements ApplicationRunner {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private BasicJPQL basicJPQL;

    @Autowired
    private JoinJPQL joinJPQL;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
        basicJPQL.run();
        joinJPQL.run();
    }

    private void init(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TestMember member1 = new TestMember();
        member1.setUserId("test1@tes.com");
        member1.setUsername("test1");
        member1.setAge(13);

        TestMember member12 = new TestMember();
        member12.setUserId("test2@tes.com");
        member12.setUsername("test2");
        member12.setAge(123);

        TestTeam testTeam = new TestTeam();
        testTeam.setTeamId("A");
        testTeam.setTeamName("팀A");
        testTeam.setMembers(Arrays.asList(member1, member12));
        member1.setTeam(testTeam);
        member12.setTeam(testTeam);

        entityManager.persist(testTeam);

        TestMember member3 = new TestMember();
        member3.setUserId("tes32@tes.com");
        member3.setUsername("test3");
        member3.setAge(23);

        TestTeam testTeam2 = new TestTeam();
        testTeam2.setTeamId("B");
        testTeam2.setTeamName("팀B");
        testTeam2.setMembers(Collections.singletonList(member3));
        member3.setTeam(testTeam2);
        entityManager.persist(testTeam2);

        TestMember member4 = new TestMember();
        member4.setUserId("tes42@tes.com");
        member4.setUsername("test4");
        member4.setAge(231);
        entityManager.persist(member4);

        transaction.commit();
    }
}
