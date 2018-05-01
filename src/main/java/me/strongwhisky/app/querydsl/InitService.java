package me.strongwhisky.app.querydsl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by taesu on 2018-05-01.
 */
@Service
public class InitService {
    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    public void init() {
        TestMember member1 = new TestMember();
        member1.setMemberId("test1@test.com");
        member1.setName("test1");
        member1.setAge(153);

        TestMember member2 = new TestMember();
        member2.setMemberId("test2@test.com");
        member2.setName("test22");
        member2.setAge(13);

        TestMember member3 = new TestMember();
        member3.setMemberId("test3@test.com");
        member3.setName("test33");
        member3.setAge(113);

        TestMember member4 = new TestMember();
        member4.setMemberId("test4@test.com");
        member4.setName("test44");
        member4.setAge(132);

        TestTeam testTeam = new TestTeam();
        testTeam.setTeamId("A");
        testTeam.setTeamName("팀A");
        member1.setTeam(testTeam);
        member2.setTeam(testTeam);
        member3.setTeam(testTeam);

        testTeam.setMembers(Arrays.asList(member1, member2, member3));

        TestTeam testTeam1 = new TestTeam();
        testTeam1.setTeamId("B");
        testTeam1.setTeamName("팀B");

        TestMember member5 = new TestMember();
        member5.setMemberId("test5@test.com");
        member5.setName("test55");
        member5.setAge(32);
        member5.setTeam(testTeam1);

        TestMember member6 = new TestMember();
        member6.setMemberId("tes666@test.com");
        member6.setName("test6");
        member6.setAge(12);
        member6.setTeam(testTeam1);
        testTeam1.setMembers(Arrays.asList(member5, member6));

        TestTeam testTeam2 = new TestTeam();
        testTeam2.setTeamId("C");
        testTeam2.setTeamName("팀C");

        teamRepository.saveAll(Arrays.asList(testTeam, testTeam1, testTeam2));      //TestTeam의 cascade -> PERSIST, MERGE 이어야 제대로 됨
    }
}
