package me.strongwhisky.app.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-01.
 */
public interface TestMemberRepository extends JpaRepository<TestMember, String> {
}
