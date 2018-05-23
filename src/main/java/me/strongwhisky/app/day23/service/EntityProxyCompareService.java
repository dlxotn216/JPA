package me.strongwhisky.app.day23.service;

import me.strongwhisky.app.day23.domain.model.User;
import me.strongwhisky.app.day23.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by taesu on 2018-05-23.
 */
@Service
public class EntityProxyCompareService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void getRefAndFind() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user1 = User.builder()
                .userId("abc03@test.com")
                .age(13)
                .email("abc03@naver.com")
                .name("abc03").build();

        entityManager.persist(user1);
        entityManager.flush();
        entityManager.clear();

        //프록시로 조회 된 엔티티에 대해 같은 엔티티를 찾는 요청이 오면
        //원본이 아닌 처음 조회 된 프록시(refUser)를 반환 함.
        User refUser = entityManager.getReference(User.class, user1.getUserKey());
        User findUser = entityManager.find(User.class, user1.getUserKey());

        System.out.println("refUser type :" + refUser.getClass());
        System.out.println("findUser type :" + findUser.getClass());
        System.out.println(refUser == findUser);
        System.out.println("Proxy 먼저 조회할 경우 equals: " + refUser.equals(findUser));
        /*
        refUser type :class me.strongwhisky.app.day23.domain.model.User_$$_jvst211_7
        findUser type :class me.strongwhisky.app.day23.domain.model.User_$$_jvst211_7
        true
        Proxy 먼저 조회할 경우 equals: true
         */

        transaction.commit();
    }

    public void findAndGetRef() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user1 = User.builder()
                .userId("abc04@test.com")
                .age(13)
                .email("abc04@naver.com")
                .name("abc04").build();

        entityManager.persist(user1);
        entityManager.flush();
        entityManager.clear();

        //원본으로 조회 된 엔티티에 대해 프록시 조회 요청 시
        //프록시가 아닌 원본 엔티티(findUser)를 반환 함
        User findUser = entityManager.find(User.class, user1.getUserKey());
        User refUser = entityManager.getReference(User.class, user1.getUserKey());

        System.out.println("refUser type :" + refUser.getClass());
        System.out.println("findUser type :" + findUser.getClass());
        System.out.println(refUser == findUser);
        System.out.println("Find 후 프록시를 조회한 경우 equals: " + refUser.equals(findUser));
        /*
        refUser type :class me.strongwhisky.app.day23.domain.model.User
        findUser type :class me.strongwhisky.app.day23.domain.model.User
        true
        Find 후 프록시를 조회한 경우 equals: true
         */

        transaction.commit();
    }

    public void compareWithProxy() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user1 = User.builder()
                .userId("abc05@test.com")
                .age(13)
                .email("abc05@naver.com")
                .name("abc05").build();

        entityManager.persist(user1);
        entityManager.flush();
        entityManager.clear();

        User refUser = entityManager.getReference(User.class, user1.getUserKey());
        User cloned = User.builder()
                .userKey(user1.getUserKey())
                .userId("abc05@test.com")
                .age(13)
                .email("abc05@naver.com")
                .name("abc05").build();

        //refUser는 Proxy이므로 실제 데이터가 없다
        //-> User#equals에서 처럼 직접 멤버변수로 비교하지 않아야 함.
        System.out.println("동일한 Entity를 프록시와 비교 " + cloned.equals(refUser));        //false
        System.out.println("동일한 Entity를 프록시와 비교 " + refUser.equals(cloned));        //true

        //advancedEquals에선 getter를 통해서 접근
        System.out.println("동일한 Entity를 프록시와 비교 " + cloned.advancedEquals(refUser));        //true
        System.out.println("동일한 Entity를 프록시와 비교 " + refUser.advancedEquals(cloned));        //true

        transaction.commit();
    }

}
