package me.strongwhisky.app.day05.service;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day05.model.Member;
import me.strongwhisky.app.day05.model.Team;
import me.strongwhisky.app.day05.repository.MemberRepository;
import me.strongwhisky.app.day05.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by taesu on 2018-04-21.
 */
@Service
@Slf4j
public class Test2Service {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    /*
        아래 방식에선 MEMBER 테이블에 teamId가 들어가지 않음
        당연히 TEAM_ID 컬럼이 MEMBER 테이블에 존재하므로...
        -> 연관관계의 주인과 관련이 있다
        -> mappedBy 속성이 Team 엔티티에 존재하기 때문에 Member 엔티티가 연관관계의 주인임
        -> 연관관계의 주인은 외래키를 관리(등록, 수정, 삭제) 할 수 있고
        -> 연관관계의 주인이 아닌 쪽(Team)은 읽기만 가능해짐
     */
    public void addMemberAndSetToTeamAndAddTeam() {
        Member member1 = new Member();
        member1.setMemberId("DM_test1");
        member1.setMemberName("DN[Owner]");

        Member member2 = new Member();
        member2.setMemberId("DM_test2");
        member2.setMemberName("DM[managere]");

        memberRepository.saveAll(Arrays.asList(member1, member2));

        Team team = new Team();
        team.setTeamId("DM");
        team.setTeamName("Data Management");
        team.setMembers(Arrays.asList(member1, member2));

        teamRepository.save(team);
        //printAll();       만약 현재 메소드에 @Transactional 걸려있고 printAll 호출 시
        //영속성 컨텍스트가 끝나지 않은 상태이므로 DB에서 데이터를 조회해도 없다
        //-> 영속성 컨텍스트는 한 트랙잭션 단위가 default
        //=> 영속성 컨텍스트가 끝날 때 flush가 발생하므로
    }

    /**
     * 방어코드를 작성하듯이 양방향 연관관계를 설정
     */
    public void advancedTest(){
        Team team = new Team();
        team.setTeamId("OP");
        team.setTeamName("Opeartion");
        teamRepository.save(team);

        Member member1 = new Member();
        member1.setMemberId("OP_test1");
        member1.setMemberName("OP[Owner]");

        member1.setTeam(team);          //연관관계 설정 member -> team
        //team.addMember(member1);        //연관관계 설정 team -> member
        //Member의 연관관계 편의 메소드를 통해 불필요 로직 제거 및 방어코드 개선

        Member member2 = new Member();
        member2.setMemberId("OP_test2");
        member2.setMemberName("OP[managere]");

        member2.setTeam(team);          //연관관계 설정 member -> team
        //team.addMember(member1);        //연관관계 설정 team -> member
        //Member의 연관관계 편의 메소드를 통해 불필요 로직 제거 및 방어코드 개선

        memberRepository.saveAll(Arrays.asList(member1, member2));
    }

}
