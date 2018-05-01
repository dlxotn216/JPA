package me.strongwhisky.app.querydsl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-01.
 */
@Entity
@Setter
@Getter
@Table
public class TestMember {
    @Id
    private String memberId;

    private String name;

    private int age;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "TEAM_ID")
    private TestTeam team;
}
