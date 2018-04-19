package me.strongwhisky.app.day04;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day04.mode.Member;
import me.strongwhisky.app.day04.mode.Team;
import me.strongwhisky.app.day04.repository.MemberRepository;
import me.strongwhisky.app.day04.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by taesu on 2018-04-19.
 */
@Component
@Slf4j
public class Day04Runner {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    //결론적으로 Member 테이블에 teamId가 정상적으로 존재하여야 결과게 제대로 나오는 것 같으며
    //TEAM_MEMBERS 테이블에 데이터는 무의미해보임.
    public void runDayApplication() {
        makeTeamAndMemberAndSetTeamToMember();
//        makeTeamAndMemberAndSetMemtersToTeam();
//        integration();
    }

    //Member
    //Team
    //테이블에만 데이터가 들어가며 출력은 정상적이나 TEAM_MEMBERS 테이블은 사용하지 않음
    /*
    2018-04-19 22:04:36.961  INFO 5444 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : -------------------------------------------------------------------------
    2018-04-19 22:04:36.979  INFO 5444 --- [  restartedMain] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
    2018-04-19 22:04:37.071  INFO 5444 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : Lee Tae Su가 속한 팀 :개발팀
    2018-04-19 22:04:37.071  INFO 5444 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : Yoon So young가 속한 팀 :개발팀
    2018-04-19 22:04:37.071  INFO 5444 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : -------------------------------------------------------------------------
    2018-04-19 22:04:37.114  INFO 5444 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : 개발팀에 속한 멤버 :Lee Tae Su
    2018-04-19 22:04:37.114  INFO 5444 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : 개발팀에 속한 멤버 :Yoon So young
    2018-04-19 22:04:37.114  INFO 5444 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : -------------------------------------------------------------------------
     */
    private void makeTeamAndMemberAndSetTeamToMember() {
        Team team = new Team();
        team.setTeamId("Development");
        team.setTeamName("개발팀");

        teamRepository.save(team);

        Member member1 = new Member();
        member1.setId("taesu");
        member1.setUserName("Lee Tae Su");
        member1.setTeam(team);

        Member member2 = new Member();
        member2.setId("syusy");
        member2.setUserName("Yoon So young");
        member2.setTeam(team);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //이 경우엔 Member 테이블에 팀 정보만 들어가고
        //TEAM_MEMBERS 테이블에 Member와 Team에 대한 매핑 정보가 들어가지않는다
        print();
    }


    //Member
    //Team
    //TEAM_MEMBERS 테이블에 모두 데이터가 들어가나
    //Member 테이블에 teamId가 null로 들어감
    //따라서 team에 해당하는 멤버 조회 실패
    //Member가 속한 팀 없음으로 나옴
    /*
    : op1name가 속한 팀 : 없음
    2018-04-19 22:00:12.073  INFO 8756 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : op2 name가 속한 팀 : 없음
    2018-04-19 22:00:12.073  INFO 8756 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : -------------------------------------------------------------------------
    2018-04-19 22:00:12.163  INFO 8756 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : -------------------------------------------------------------------------

     */
    private void makeTeamAndMemberAndSetMemtersToTeam() {
        Member member1 = new Member();
        member1.setId("op1");
        member1.setUserName("op1name");

        Member member2 = new Member();
        member2.setId("op2");
        member2.setUserName("op2 name");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Team team = new Team();
        team.setTeamId("Operation");
        team.setTeamName("운용팀");

        team.addMember(member1);
        team.addMember(member2);

        teamRepository.save(team);
        //이 경우엔 member1, member2가 TEAM_MEMBERS 테이블에만 저장되고
        //member 테이블에 teamId 정보가 들어가지 않는다.

        print();
    }

    //Member 테이블에 teamId 제대로 들어감
    //Team Members 테이블에 데이터 제대로 들어감
    /*
    2018-04-19 22:02:00.705  INFO 692 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : -------------------------------------------------------------------------
    2018-04-19 22:02:00.727  INFO 692 --- [  restartedMain] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
    2018-04-19 22:02:00.817  INFO 692 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : dm1 name가 속한 팀 :DM팀
    2018-04-19 22:02:00.817  INFO 692 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : dm2 name가 속한 팀 :DM팀
    2018-04-19 22:02:00.817  INFO 692 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : -------------------------------------------------------------------------
    2018-04-19 22:02:00.861  INFO 692 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : DM팀에 속한 멤버 :dm1 name
    2018-04-19 22:02:00.861  INFO 692 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : DM팀에 속한 멤버 :dm2 name
    2018-04-19 22:02:00.861  INFO 692 --- [  restartedMain] me.strongwhisky.app.day04.Day04Runner    : -------------------------------------------------------------------------
     */
    private void integration() {
        Member member1 = new Member();
        member1.setId("dm1");
        member1.setUserName("dm1 name");

        Member member2 = new Member();
        member2.setId("dm2");
        member2.setUserName("dm2 name");
        memberRepository.save(member1);     //추가를 안할 경우 FK에 의해 에러
        memberRepository.save(member2);     //추가를 안할 경우 FK에 의해 에러

        Team team = new Team();
        team.setTeamId("DataManagement");
        team.setTeamName("DM팀");

        team.addMember(member1);
        team.addMember(member2);
        teamRepository.save(team);

        member1.setTeam(team);
        member2.setTeam(team);
        memberRepository.save(member1);
        memberRepository.save(member2);

        print();
    }

    private void print() {
        log.info("-------------------------------------------------------------------------");
        memberRepository.findAll().forEach(item -> {
            Team t = item.getTeam();
            if (t != null) {
                log.info(item.getUserName() + "가 속한 팀 :" + t.getTeamName());
            } else {
                log.info(item.getUserName() + "가 속한 팀 : 없음");   //output
            }
        });

        log.info("-------------------------------------------------------------------------");

        //item.getMembers 하면 failed to lazily initialize a collection of role 에러
        //세션이 끝나서 그럴수 있다고 함
        //첫번째는 Team.java OneToMany에 fetch = FetchType.EAGER 추가
        //두번째는 테스트 코드에 @Transactional을 추가.
        teamRepository.findAll().forEach(item -> {
            List<Member> members = memberRepository.findAllByTeam(item);
            if (!CollectionUtils.isEmpty(members)) {
                members.forEach(member -> {
                    log.info(item.getTeamName() + "에 속한 멤버 :" + member.getUserName());
                });
            }
        });
        log.info("-------------------------------------------------------------------------\n\n");
    }
}
