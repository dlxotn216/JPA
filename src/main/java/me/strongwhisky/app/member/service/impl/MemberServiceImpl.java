package me.strongwhisky.app.member.service.impl;

import me.strongwhisky.app.member.exception.DuplicatedMemberIdException;
import me.strongwhisky.app.member.exception.MemberNotFoundException;
import me.strongwhisky.app.member.domain.Member;
import me.strongwhisky.app.member.repository.MemberRepository;
import me.strongwhisky.app.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-05-07.
 */
@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member join(Member member) {
        validationForDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validationForDuplicateMember(Member member) {
        if (memberRepository.findByUserId(member.getUserId()).isPresent()) {
            throw new DuplicatedMemberIdException(member.getUserId() + "는 이미 존재하는 사용자 ID입니다.");
        }
    }

    @Override
    public Member findByKey(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member findById(String userId) {
        return memberRepository
                .findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);
    }
}
