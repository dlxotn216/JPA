package me.strongwhisky.app.jpql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

/**
 * Created by taesu on 2018-05-01.
 */
@Service
public class BasicJPQL {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void run() {
        queryAndTypeQuery();
        prarmerterBinding();
        paging();
        groupBy();
    }

    private void queryAndTypeQuery() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println();
        System.out.println("반드시 username, age만 필요한 경우");
        Query query =
                entityManager.createQuery("SELECT m.username, m.age FROM TestMember m");
        List results = query.getResultList();

        for (Object o : results) {
            Object[] result = (Object[]) o;
            System.out.println("userName :" + result[0]);
            System.out.println("age :" + result[1]);
        }

        System.out.println();
        System.out.println("필요하다면 new를 통해 새로운 DTF를 받아라");
        TypedQuery<TestUserDto> query2 =
                entityManager.createQuery("SELECT new me.strongwhisky.app.jpql.TestUserDto(m.username, m.age) FROM TestMember m", TestUserDto.class);
        List<TestUserDto> testUserDtos = query2.getResultList();
        for (TestUserDto member : testUserDtos) {
            System.out.println("type username :" + member.getUsername());
            System.out.println("type age :" + member.getAge());
        }

        System.out.println();
        System.out.println("TestMember의 모든 필드 조회");
        TypedQuery<TestMember> typedQuery
                = entityManager.createQuery("SELECT m FROM TestMember m", TestMember.class);
        List<TestMember> members = typedQuery.getResultList();
        for (TestMember member : members) {
            System.out.println("type userId :" + member.getUserId());
            System.out.println("type username :" + member.getUsername());
            System.out.println("type age :" + member.getAge());
        }


    }

    private void prarmerterBinding() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println();
        System.out.println("반드시 파라미터 바인딩은 이름기준 파라미터로 (순서와 타입이 일치하는 생성자 필요)");
        TypedQuery<TestMember> typedQuery1
                = entityManager.createQuery("SELECT m FROM TestMember m WHERE m.userId = :userId", TestMember.class);   //이름기준 파라미터
        typedQuery1.setParameter("userId", "test1@tes.com");                                                          //이름기준 파라미터 바인딩
        TestMember type1Result = typedQuery1.getSingleResult(); //반드시 하나의 결과가 있어야 한다. 0 or 2이면 exception
        System.out.println("single result username :" + type1Result.getUsername());
        System.out.println("single result age :" + type1Result.getAge());

        System.out.println();
        TypedQuery<TestMember> typedQuery2
                = entityManager.createQuery("SELECT m FROM TestMember m WHERE m.userId = ?1", TestMember.class);   //위치기준 파라미터 -> sql injection 취약점
        typedQuery2.setParameter(1, "test1@tes.com");                                                       //위치기준 파라미터 바인딩
        TestMember type1Resul2 = typedQuery2.getSingleResult(); //반드시 하나의 결과가 있어야 한다. 0 or 2이면 exception
        System.out.println("single result username :" + type1Resul2.getUsername());
        System.out.println("single result age :" + type1Resul2.getAge());
    }

    private void paging() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println();
        System.out.println("페이징 쿼리를 DB마다 방언으로 보유");
        TypedQuery<TestMember> typedQuery1
                = entityManager.createQuery("SELECT m FROM TestMember m ORDER BY m.username DESC ", TestMember.class);
        typedQuery1.setFirstResult(0);      //1부터
        typedQuery1.setMaxResults(10);      //10개 가져와
        //1 ~ 10
        List<TestMember> members = typedQuery1.getResultList();
        for (TestMember member : members) {
            System.out.println("type userId :" + member.getUserId());
            System.out.println("type username :" + member.getUsername());
            System.out.println("type age :" + member.getAge());
        }
    }

    private void groupBy() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println();
        TypedQuery<TestUserStatistic> typedQuery1
                = entityManager.createQuery("SELECT NEW me.strongwhisky.app.jpql.TestUserStatistic(COUNT(m), SUM(m.age), MAX(m.age), MIN(m.age), AVG(m.age)) from TestMember m", TestUserStatistic.class);

        TestUserStatistic testUserStatistic = typedQuery1.getSingleResult();
        System.out.println(testUserStatistic.getTotalCount());
        System.out.println(testUserStatistic.getMaxAge());
        System.out.println(testUserStatistic.getMinAge());
        System.out.println(testUserStatistic.getSumAvg());
        System.out.println(testUserStatistic.getAvgAge());
    }
}
