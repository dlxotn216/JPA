package me.strongwhisky.app.jpql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by taesu on 2018-05-01.
 */
@Service
public class JoinJPQL {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void run() {
        innerJoinOuterJoin();
        fetchJoin();
    }

    private void innerJoinOuterJoin() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String teamId = "A";
        String query = "SELECT m FROM TestMember m INNER JOIN m.team t WHERE t.teamId = :teamId";

        System.out.println();
        System.out.println("INNER JOIN시에도 반드시 별칭은 필수, ON 절이 없음, JOIN 다음 조인 할 객체의 연관 필드를 명시 함");
        List<TestMember> members = entityManager.createQuery(query, TestMember.class).setParameter("teamId", teamId).getResultList();
        for (TestMember member : members) {
            System.out.println("type userId :" + member.getUserId());
            System.out.println("type username :" + member.getUsername());
            System.out.println("type age :" + member.getAge());
        }

        query = "SELECT m FROM TestMember m LEFT JOIN m.team t";

        System.out.println();
        System.out.println("OUTER JOIN시에도 반드시 별칭은 필수, ON 절이 없음, JOIN 다음 조인 할 객체의 연관 필드를 명시 함");
        members = entityManager.createQuery(query, TestMember.class).getResultList();
        for (TestMember member : members) {
            System.out.println("type userId :" + member.getUserId());
            System.out.println("type username :" + member.getUsername());
            System.out.println("type age :" + member.getAge());
        }
    }

    public void fetchJoin() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println();
        System.out.println("INNER JOIN 하면서 (Entity) fetch join 하여 team 관련 정보도 가져온다 (fetch join에는 alias 불가)");
        List<TestMember> members =  entityManager.createQuery("SELECT m FROM TestMember m INNER JOIN FETCH m.team", TestMember.class).getResultList();
        for (TestMember member : members) {
            System.out.println("type userId :" + member.getUserId());
            System.out.println("type username :" + member.getUsername());
            System.out.println("type age :" + member.getAge());
            System.out.println("team :" + member.getTeam().getTeamId() + "::" + member.getTeam().getTeamName());        //INNER 조인이므로 team은 항상 null이 아님
        }

        System.out.println();
        System.out.println("INNER JOIN 하면서 (Collection) fetch join 하여 team 관련 정보도 가져온다 (fetch join에는 alias 불가)");
        List<TestTeam> teams =  entityManager.createQuery("SELECT t FROM TestTeam t INNER JOIN FETCH t.members", TestTeam.class).getResultList();
        for (TestTeam testTeam : teams) {
            System.out.println(testTeam.getTeamName()+"'s member list ");
            for(TestMember testMember : testTeam.getMembers()){
                System.out.println(testMember.getUserId());
            }
        }

        //주의 위처럼 할 경우 Team에 해당하는 Member가 있는 수 만큼 전체 Row가 나온다 (Mybatis에선 resultMap에서 collections을 통해 자동 매핑을 지원한다)
        System.out.println();
        System.out.println("INNER JOIN 하면서 (Collection) fetch join 할 때는 반드시 DISTINCT 옵션을");
        teams =  entityManager.createQuery("SELECT DISTINCT t FROM TestTeam t INNER JOIN FETCH t.members", TestTeam.class).getResultList();
        for (TestTeam testTeam : teams) {
            System.out.println(testTeam.getTeamName()+"'s member list ");
            for(TestMember testMember : testTeam.getMembers()){
                System.out.println(testMember.getUserId());
            }
        }
        //컬렉션 Fetch join 할 경우 페이징이 메모리 상에서 일어남 또는 지원하지 않는 JPA 구현체가 잇다

    }


}
