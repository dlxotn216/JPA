package me.strongwhisky.app.day05.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-21.
 */
@Entity
@Data
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
