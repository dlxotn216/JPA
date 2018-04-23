package me.strongwhisky.app.day06.onetomany.unidirectional;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by taesu on 2018-04-23.
 *
 * 일대다 단방향 관계에서 Team
 *
 * 연관관계 처리를 위해 Member 등록 시 update가 반드시 동반되어야 하는 단점이 있다
 *
 * insert member 1
 * insert member 2
 * insert team (team.setMembers(member1, member2)
 * -> INSERT TEAM, UPDATE MEMBER1, UPDATE MEMBER2
 *
 */
@Entity @Data
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String teamId;

    private String teamName;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")   //Member 테이블으 TEAM_ID
    private List<Member> memberList = new ArrayList<>();
}
