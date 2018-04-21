package me.strongwhisky.app.day05.repository;

import me.strongwhisky.app.day05.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-04-21.
 */
public interface MemberRepository extends JpaRepository<Member, String> {
}
