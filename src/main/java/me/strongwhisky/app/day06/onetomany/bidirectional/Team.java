package me.strongwhisky.app.day06.onetomany.bidirectional;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by taesu on 2018-04-23.
 *
 * 일대다 양방향 관계에서 Team
 *
 * 일대다 양방향 관계를 억지르 만들어 낸 모양으로
 * 읽기전용 Mapping 을 통해 흉내냄
 *
 */
//@Entity @Data
//@ToString(exclude = "members")
//@EqualsAndHashCode(exclude = "members")
public class Team {

//    @Id @GeneratedValue
//    @Column(name = "TEAM_ID")
//    private Long teamId;
//
//    private String teamName;
//
//    //List, Map, Set 사용 가능
//    @OneToMany(mappedBy = "team")
//    @JoinColumn(name = "TEAM_ID")
//    private List<Member> members;

    //현재 버전에서 @OneToMany 어노테이션에 mappedBy 썼을 경우
    //JoinColumn, JoinTable 매핑이 불가능한 에러 발생
    //Associations marked as mappedBy must not define database mappings like @JoinTable or @JoinColumn: me.strongwhisky.app.day06.onetomany.bidirectional.Team.members
}
