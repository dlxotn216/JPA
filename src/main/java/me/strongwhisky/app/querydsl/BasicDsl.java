package me.strongwhisky.app.querydsl;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.SearchResults;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPADeleteClause;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.jpa.impl.JPAUpdateClause;
import com.mysema.query.types.Projections;
import com.mysema.query.types.QTuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.Map;

import static me.strongwhisky.app.day11.model.member.QMember.member;

/**
 * Created by taesu on 2018-05-01.
 */
@Service
public class BasicDsl {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void run() {
        basicSearch();
        join();
        subQuery();
        others();
        batchQuery();
        dynamicQuery();
    }

    private void basicSearch() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("직접 지정한 인스턴스 사용");
        JPAQuery query = new JPAQuery(entityManager);
        QTestMember qMember = new QTestMember("m"); //직접 별칭을 지정한 인스턴스 (서브쿼리 같은 경우 지정하여 사용)
        query.from(qMember)
                .where(qMember.name.eq("test22"))
                .orderBy(qMember.name.desc())
                .list(qMember)
                .forEach(member -> {
                    System.out.println(member.getName());
                });

        //미동작
        System.out.println("기본 인스턴스 사용");
        entityManager = entityManagerFactory.createEntityManager();
        new JPAQuery(entityManager)
                .from(member)       //기본 인스턴스
                .orderBy(member.name.desc())
                .list(member)
                .forEach(member -> {
                    System.out.println(member.getName());
                });

        System.out.println("조건식 예제");
        QTestMember qTestMember = new QTestMember("members1");
        new JPAQuery(entityManager)
                .from(qTestMember)
                .where(
                        qTestMember.age.gt(12)
                                .or(
                                        qTestMember.name.contains("test").and(qTestMember.memberId.like("%@%"))     //contains == like
                                ))
                //.uniqueResult(qTestMember)  //조회 결과가 하나일 때 (없으면 null, 둘 이상이면 NonUniqueResultException
                //.singleResult()           //결과가 하나이상이면 처음 row만 반환
                .list(qTestMember)
                .forEach(item -> System.out.println(item.getName() + ":" + item.getMemberId() + ":" + item.getAge()));

        System.out.println("정렬 및 페이징");
        new JPAQuery(entityManager)
                .from(qTestMember)
                .orderBy(qTestMember.age.desc(), qTestMember.memberId.desc())
                .offset(0).limit(2)     //0부터 두개 (즉 1, 2)
                .list(qTestMember)
                .forEach(item -> System.out.println(item.getName() + ":" + item.getMemberId() + ":" + item.getAge()));

        System.out.println("실용적 사용 법");
        SearchResults<TestMember> results = new JPAQuery(entityManager)
                .from(qTestMember)
                .orderBy(qTestMember.age.desc(), qTestMember.memberId.desc())
                .offset(0).limit(2)
                .listResults(qTestMember);

        System.out.println("total is :" + results.getTotal());
        results.getResults().forEach(item -> System.out.println(item.getName() + ":" + item.getMemberId() + ":" + item.getAge()));
    }

    private void join() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("left 조인 적용");
        JPAQuery query = new JPAQuery(entityManager);
        QTestTeam qTestTeam = new QTestTeam("TEAA");
        QTestMember qMember = new QTestMember("mEEEE");

        query.from(qTestTeam)
                .leftJoin(qTestTeam.members, qMember)
//                .fetch()                                //fetch join
                .distinct()                             //JPQL과 마찬가지로 distinct 적용
                .list(qTestTeam)
                .forEach(item -> {
                    System.out.println(item.getTeamId() + "'s members");
                    for (TestMember member : item.getMembers()) {          //entityManger.close()가 이전에 호출되면 Lazy... exception 발생할 것임
                        System.out.println(member.getMemberId());           //query 객체를 새로 생성하지 않고 QTestMember 기존 객체 생성 할 경우 already used 에러 발생
                    }                                                       //query 객체를 새로 생성하던지 QTestMember를 새로 생성하던지 해야 함
                    System.out.println();
                });
    }

    private void subQuery() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("서브 쿼리");
        JPAQuery query = new JPAQuery(entityManager);
        QTestMember qMember = new QTestMember("awefawefawf");
        QTestMember qMemberSub = new QTestMember("abcaeafefaew");

        query.from(qMember)
                .where(qMember.age.goe(
                        new JPASubQuery().from(qMemberSub).unique(qMember.age.avg())            //SubQuery 객체
                ))
                .list(qMember)
                .forEach(item -> System.out.println(item.getName()));
    }

    private void others() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("이름만 조회");
        JPAQuery query = new JPAQuery(entityManager);
        QTestMember qMember = new QTestMember("forname");

        new JPAQuery(entityManager).from(qMember)
                .list(qMember.name)
                .forEach(System.out::println);


        System.out.println("이름 나이만 조회");
        new JPAQuery(entityManager).from(qMember)
                .list(new QTuple(qMember.name, qMember.age))
                .forEach(item -> {
                    System.out.println(item.get(qMember.name) + "is " + item.get(qMember.age) + " years old");
                });


        System.out.println("팀별 멤버의 통계 정보 조회");
        QTestTeam testTeam = new QTestTeam("aefawfawefawfe");
        new JPAQuery(entityManager)
                .from(testTeam)
                .leftJoin(testTeam.members, qMember)
                .distinct()
                .groupBy(testTeam.teamId)
                .orderBy(qMember.count().asc(), testTeam.teamId.asc())
                .list(Projections.bean(
                        TeamStatistic.class,
                        testTeam.as("testTeam"),
                        qMember.age.max().as("maxMemberAge"),
                        qMember.age.min().as("minMemberAge"),
                        qMember.age.avg().as("avgMemberAge"),
                        qMember.age.sum().as("sumMemberAge"),
                        qMember.count().as("userCount")))
                .forEach(System.out::println);

    }

    private void batchQuery() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityManagerTransaction = entityManager.getTransaction();

        System.out.println("배치 쿼리");
        QTestMember qMember = new QTestMember("aefawf");

        entityManagerTransaction.begin();
        new JPAUpdateClause(entityManager, qMember)
                .where(qMember.memberId.eq("test1@test.com"))
                .set(qMember.age, 100)
                .execute();
        entityManagerTransaction.commit();

        TestMember member = new JPAQuery(entityManager)
                .from(qMember)
                .where(qMember.memberId.eq("test1@test.com"))
                .uniqueResult(qMember);
        System.out.println(member == null ? "null" : member.getAge());

        entityManagerTransaction.begin();
        new JPADeleteClause(entityManager, qMember)
                .where(qMember.memberId.eq("test1@test.com"))
                .execute();
        entityManagerTransaction.commit();

        member = new JPAQuery(entityManager)
                .from(qMember)
                .where(qMember.memberId.eq("test1@test.com"))
                .uniqueResult(qMember);
        System.out.println(member == null ? "deleted" : "not deleted");

    }

    private void dynamicQuery() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<String, Object> param = new HashMap<>();
        param.put("memberId", "test2@test.com");

        System.out.println("동적 쿼리");
        QTestMember qMember = new QTestMember("aefawf");

        //mybatis if 처럼 동적 쿼리 생성 (Where 절만 가능)
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (param.containsKey("memberId")) {
            booleanBuilder.and(qMember.memberId.contains((String) param.get("memberId")));
        }

        TestMember member = new JPAQuery(entityManager)
                .from(qMember)
                .where(booleanBuilder.and(qMember.age.gt(1)))
                .uniqueResult(qMember);
        System.out.println(member == null ? "not found " : "founded");


        System.out.println("@QueryDelegate 어노테이션을 통한 메소드 위임을 이용하여 동적 쿼리 (30 이하의 멤버 구하기)");
        new JPAQuery(entityManager)
                .from(qMember)
                .where(qMember.isYounggerMember(30))
                .list(qMember)
                .forEach(item -> {
                    System.out.println(item.getName() + "is younger than 30 [" + item.getAge() + "]");
                });

    }
}
