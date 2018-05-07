package me.strongwhisky.app.day11.member.repository;

import me.strongwhisky.app.day11.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by taesu on 2018-05-07.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);
}
