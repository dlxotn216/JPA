package me.strongwhisky.app.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-01.
 */
@Entity(name = "TestMember")
@Getter
@Setter
@Table(name = "TEST_MEMBER")
public class TestMember {
    @Id
    private String userId;

    private String username;

    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TestTeam team;
}
