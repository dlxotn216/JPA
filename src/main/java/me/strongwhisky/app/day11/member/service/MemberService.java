package me.strongwhisky.app.day11.member.service;

import me.strongwhisky.app.day11.member.model.Member;

/**
 * Created by taesu on 2018-05-07.
 */
public interface MemberService {
    Member join(Member member);

    Member findByKey(Long memberId);

    Member findById(String userId);
}
