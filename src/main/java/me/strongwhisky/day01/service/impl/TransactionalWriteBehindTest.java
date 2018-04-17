package me.strongwhisky.day01.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.day01.model.User;
import me.strongwhisky.day01.repository.UserRepository;
import me.strongwhisky.day01.service.TransactionPersistTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by taesu on 2018-04-16.
 */
@Component
@Slf4j
public class TransactionalWriteBehindTest {

    private final UserRepository userRepository;

    private final EntityManagerFactory entityManagerFactory;

    private final TransactionPersistTest persistTest;

    @Autowired
    public TransactionalWriteBehindTest(UserRepository userRepository, EntityManagerFactory entityManagerFactory, TransactionPersistTest persistTest) {
        this.userRepository = userRepository;
        this.entityManagerFactory = entityManagerFactory;
        this.persistTest = persistTest;
    }

    public void run() {
        JPATest();
        HibernateTest();

        persistTest.test();
    }

    private void JPATest() {
        User user = new User();
        user.setName("Lee Tae Su");
        user.setEmail("dlxotn@crcube.co.kr");
        user.setJoinedAt(new Date());
        userRepository.save(user);          //ID will be 5
        //tx1 끝 (영속성 컨텍스트1)

        //영속 에티티의 동일성 보장되지 않음
        //EntityManager이 EntytiTransaction을 직접 사용하는 것이아니므로
        //기본적으로 Spring container의 기본 영속성 컨텍스트 전략은 트랜잭션 범위의 영속성 컨텍스트 전략을 사용
        //트랜잭션의 범위와 영속성 컨텍스트의 생존 범위가 같다 == 같은 트랜잭션이면 같은 영속성을 쓴다
        userRepository.findById(5L).ifPresent((userFromResponse) -> {
            log.info("idEqual? " + (user == userFromResponse));
        });
        //tx2 끝 (영속성 컨텍스트2)

        userRepository.findAll().stream().
                filter(item -> item.getEmail().equals(user.getEmail()))
                .collect(Collectors.toList())
                .forEach(item -> log.info("isEqual ? " + (item == user)));
        //tx3 끝 (영속성 컨텍스트3)
    }

    private void HibernateTest() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user = new User();
        user.setName("Lee Tae Su");
        user.setEmail("dlxotn12345@crcube.co.kr");
        user.setJoinedAt(new Date());
        entityManager.persist(user);        //ID will be 6

        //true
        log.info("is Equal in EntityTransaction before commit :" + (entityManager.find(User.class, 6L) == user));
        transaction.commit();

        //커밋후에도 true
        //커밋 된 entity는 1차 캐시(메모리)에 존재하므로
        log.info("is Equal in EntityTransaction after commit :" + (entityManager.find(User.class, 6L) == user));

        transaction = entityManager.getTransaction();
        transaction.begin();
        //변경사항이 알아서 감지 되어 commit 시 (flush 할때) 변경 쿼리가 날아감
        entityManager.find(User.class, 6L).setEmail("test@test.com");
        transaction.commit();
        log.info("Change email -> " + (entityManager.find(User.class, 6L).getEmail()));

        transaction = entityManager.getTransaction();
        transaction.begin();

        //remove를 호출하는 순간 영속성 컨텍스트에서 제거됨
        //따라서 변경사항 감지 일어나지 않음
        entityManager.remove(entityManager.find(User.class, 6L));

        //null
        log.info("is removed yet before commit? " + entityManager.find(User.class, 6L));
        transaction.commit();

        //null
        log.info("is removed yet after commit ? " + entityManager.find(User.class, 6L));


    }
}
