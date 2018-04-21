package me.strongwhisky.app.day05.service;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day05.model.Member;
import me.strongwhisky.app.day05.model.Team;
import me.strongwhisky.app.day05.repository.MemberRepository;
import me.strongwhisky.app.day05.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by taesu on 2018-04-21.
 */
@Service
@Slf4j
public class Test1Service {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;


    public void addTaemAndSetToMemberAndAddMember() {
        Team team = new Team();
        team.setTeamId("DEV");
        team.setTeamName("Development");

        teamRepository.save(team);

        Member member1 = new Member();
        member1.setMemberId("dlxotn2345");
        member1.setMemberName("Dl xo tn");
        member1.setTeam(team);

        Member member2 = new Member();
        member2.setMemberId("taesu");
        member2.setMemberName("Lee Tae Su");
        member2.setTeam(team);

        memberRepository.saveAll(Arrays.asList(member1, member2));
        //printAll();       만약 현재 메소드에 @Transactional 걸려있고 printAll 호출 시
        //영속성 컨텍스트가 끝나지 않은 상태이므로 DB에서 데이터를 조회해도 없다
        //-> 영속성 컨텍스트는 한 트랙잭션 단위가 default
        //=> 영속성 컨텍스트가 끝날 때 flush가 발생하므로

    }

}
