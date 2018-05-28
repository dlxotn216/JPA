package me.strongwhisky.app.day26.service;

import me.strongwhisky.app.day26.domain.model.User;
import me.strongwhisky.app.day26.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taesu on 2018-05-24.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /**
     * ----------------------readByDefault----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::Lee Tae 11111
     * update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
     * 01@test.com::readByDefault
     * ----------------------End of transaction print----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByDefault
     */
    @Override
    public void readByDefault() {
        System.out.println("----------------------readByDefault----------------------");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, 1L);
        System.out.println(user.getUserId() + "::" + user.getName());

        user.setName("readByDefault");
        entityTransaction.commit();

        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        user = entityManager.find(User.class, 1L);
        System.out.println(user.getUserId() + "::" + user.getName());

        entityTransaction.commit();
    }

    /**
     * ----------------------readByScalarType----------------------
     * select user0_.user_id as col_0_0_, user0_.name as col_1_0_ from app_user user0_
     * 01@test.com::readByDefault
     * 02@test.com::Lee Tae Su2222
     * 03@test.com::aefeawfawf
     * 123@test.com::Lee Tae 11111
     * 123123@test.com::Lee Tae Su2222
     * 324124124@test.com::aefeawfawf
     * select user0_.user_id as col_0_0_, user0_.name as col_1_0_ from app_user user0_
     * 01@test.com::readByDefault
     * 02@test.com::Lee Tae Su2222
     * 03@test.com::aefeawfawf
     * 123@test.com::Lee Tae 11111
     * 123123@test.com::Lee Tae Su2222
     * 324124124@test.com::aefeawfawf
     * ----------------------End of transaction print----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByDefault
     */
    @Override
    public void readByScalarType() {
        System.out.println("----------------------readByScalarType----------------------");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // select user0_.user_id as col_0_0_, user0_.name as col_1_0_ from app_user user0_
        entityManager.createQuery("select u.userId, u.name from User u")
                .getResultList().forEach(item -> {
            Object[] result = (Object[]) item;
            System.out.println(result[0] + "::" + result[1]);
        });

        // select user0_.user_id as col_0_0_, user0_.name as col_1_0_ from app_user user0_
        entityManager.createQuery("select u.userId, u.name from User u")
                .getResultList().forEach(item -> {
            Object[] result = (Object[]) item;
            System.out.println(result[0] + "::" + result[1]);
        });

        entityTransaction.commit();
    }

    /**
     * ----------------------readByReadOnlyQueryHints----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByDefault
     * update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
     * 01@test.com::readByReadOnlyQueryHints
     * ----------------------End of transaction print----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByReadOnlyQueryHints
     */
    @Override
    public void readByReadOnlyQueryHints() {
        System.out.println("----------------------readByReadOnlyQueryHints----------------------");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Map<String, Object> hints = new HashMap<>();
        hints.put("org.hibernate.readOnly", true);
        User user = entityManager.find(User.class, 1L, hints);
        System.out.println(user.getUserId() + "::" + user.getName());

        user.setName("readByReadOnlyQueryHints");

        //또는 아래처럼 JPQL에 Hint 적용
        //select user0_.user_key as user_key1_2_, user0_.age as age2_2_, user0_.email as email3_2_, user0_.group_key as group_ke6_2_, user0_.name as name4_2_, user0_.user_id as user_id5_2_ from app_user user0_
//        entityManager.createQuery("select u from User u", User.class)
//                .setHint("org.hibernate.readOnly", true)
//                .getResultList().forEach(item -> {
//            System.out.println(item.getUserId() + "::" + item.getName());
//        });
        entityTransaction.commit();

        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        user = entityManager.find(User.class, 1L, hints);
        System.out.println(user.getUserId() + "::" + user.getName());
        entityTransaction.commit();
    }

    /**
     * ----------------------readByReadOnlyTransaction----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByReadOnlyQueryHints
     * update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
     * 01@test.com::readByReadOnlyTransaction
     * ----------------------End of transaction print----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByReadOnlyTransaction
     */
    @Transactional(readOnly = true)
    @Override
    public void readByReadOnlyTransaction() {
        System.out.println("----------------------readByReadOnlyTransaction----------------------");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, 1L);
        System.out.println(user.getUserId() + "::" + user.getName());

        user.setName("readByReadOnlyTransaction");

        entityTransaction.commit();

        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        user = entityManager.find(User.class, 1L);
        System.out.println(user.getUserId() + "::" + user.getName());

        entityTransaction.commit();
    }

    /**
     * ----------------------readByNotSupportransaction----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByReadOnlyTransaction
     * update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
     * 01@test.com::readByNotSupportransaction
     * ----------------------End of transaction print----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByNotSupportransaction
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void readByNotSupportransaction() {
        System.out.println("----------------------readByNotSupportransaction----------------------");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        User user = entityManager.find(User.class, 1L);
        System.out.println(user.getUserId() + "::" + user.getName());

        user.setName("readByNotSupportransaction");

        entityTransaction.commit();

        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        user = entityManager.find(User.class, 1L);
        System.out.println(user.getUserId() + "::" + user.getName());

        entityTransaction.commit();
    }

    /**
     * ----------------------readByReadOnlyTransactionAndRepository----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByNotSupportransaction
     * 01@test.com::readByReadOnlyTransactionAndRepository
     * ----------------------End of transaction print----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByNotSupportransaction
     */
    @Transactional(readOnly = true)
    @Override
    public void readByReadOnlyTransactionAndRepository() {
        System.out.println("----------------------readByReadOnlyTransactionAndRepository----------------------");
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(user.getUserId() + "::" + user.getName());

        user.setName("readByReadOnlyTransactionAndRepository");

        userRepository.save(user);

        user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(user.getUserId() + "::" + user.getName());
    }


    /**
     * ----------------------readByNotSupportedTransactionAndRepository----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByNotSupportransaction
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * update app_user set age=?, email=?, group_key=?, name=?, user_id=? where user_key=?
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByNotSupportedTransactionAndRepository
     * ----------------------End of transaction print----------------------
     * select user0_.user_key as user_key1_2_0_, user0_.age as age2_2_0_, user0_.email as email3_2_0_, user0_.group_key as group_ke6_2_0_, user0_.name as name4_2_0_, user0_.user_id as user_id5_2_0_ from app_user user0_ where user0_.user_key=?
     * 01@test.com::readByNotSupportedTransactionAndRepository
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void readByNotSupportedTransactionAndRepository() {
        System.out.println("----------------------readByNotSupportedTransactionAndRepository----------------------");
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(user.getUserId() + "::" + user.getName());

        user.setName("readByNotSupportedTransactionAndRepository");

        userRepository.save(user);

        user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(user.getUserId() + "::" + user.getName());
    }
}
