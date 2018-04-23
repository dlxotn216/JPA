package me.strongwhisky.app.day06.manytoone.bidirectional;

import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-04-23.
 */
@Service
public class Test {
    public void test() {
        Member member1 = new Member();
        member1.setMemberId("test1");

        Member member2 = new Member();
        member2.setMemberId("test2");

        Member member3 = new Member();
        member3.setMemberId("test3");

        Team team1 = new Team();
        team1.setTeamId("team1");
        team1.addMember(member1);

        member1.setTeam(team1);
        member2.setTeam(team1);
        member3.setTeam(team1);


        Team team2 = new Team();
        team2.setTeamId("team2");
        team2.addMember(member3);

        member3.setTeam(team1);
        member3.setTeam(team2);

        System.out.println(member1.getTeam().getTeamId());
        System.out.println(member2.getTeam().getTeamId());
        System.out.println(member3.getTeam().getTeamId());

        System.out.println();

        System.out.println(team1.getMembers());
        System.out.println(team2.getMembers());
    }
}
