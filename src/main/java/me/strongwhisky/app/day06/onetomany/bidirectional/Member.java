package me.strongwhisky.app.day06.onetomany.bidirectional;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by taesu on 2018-04-23.
 *
 * 일대다 양방향 관계에서의 Member
 *
 * 일대다 양방향은 사실 없지만 있는 것처럼 만들기 위해
 * Member 테이블의 TEAM_ID를 insert, update 할 수 없게 처리 한 것
 */
//@Entity @Data
public class Member {

//    @Id
//    private String memberId;
//
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
//    private Team team;

}

