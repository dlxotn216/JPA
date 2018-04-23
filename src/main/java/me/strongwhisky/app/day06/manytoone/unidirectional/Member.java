package me.strongwhisky.app.day06.manytoone.unidirectional;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대일 단방향 관계에서 Member (양반향과 다를 것이 없다)
 *
 * Member는 연관관계의 주인이다
 * 언제나 FK를 가진 쪽이 연관관계의 주인이며
 * ManyToOne 어노테이션이 선언되어있다
 */
@Entity @Data
public class Member {

    @Id
    private String memberId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

}
