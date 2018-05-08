package me.strongwhisky.app.day11.member.service.impl;

import me.strongwhisky.app.member.exception.DuplicatedMemberIdException;
import me.strongwhisky.app.member.domain.Member;
import me.strongwhisky.app.member.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


/**
 * Created by taesu on 2018-05-07.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setUserId("taesu@crscube.co.kr");
        member.setName("lee taesu");

        //When
        Member joinedMember = memberService.join(member);

        //Then
        assertThat(joinedMember).isEqualToIgnoringGivenFields(joinedMember, "orders");
    }

    @Test(expected = DuplicatedMemberIdException.class)
    public void 중복회원가입방지() throws Exception {
        //Given
        Member member = new Member();
        member.setUserId("taesu@crscube.co.kr");
        member.setName("lee taesu");
        memberService.join(member);

        //When
        Member duplicatedMember = new Member();
        duplicatedMember.setUserId(member.getUserId());
        duplicatedMember.setName("lee taesu 2");
        memberService.join(duplicatedMember);

        //Then
        fail("중복 된 사용자가 성공적으로 등록 되었습니다.");
    }
}