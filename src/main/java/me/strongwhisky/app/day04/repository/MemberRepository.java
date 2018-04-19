package me.strongwhisky.app.day04.repository;

import me.strongwhisky.app.day04.mode.Member;
import me.strongwhisky.app.day04.mode.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by taesu on 2018-04-19.
 */
public interface MemberRepository extends JpaRepository<Member, String> {

    List<Member> findAllByTeam(Team team);
}
