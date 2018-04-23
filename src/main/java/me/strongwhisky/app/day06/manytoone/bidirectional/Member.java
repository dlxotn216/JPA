package me.strongwhisky.app.day06.manytoone.bidirectional;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대일 양방향 관계에서 Member (단반향과 다를 것이 없다)
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


    public void setTeam(Team team){
        if(this.team != null && this.team != team && team != null){
            this.team.removeMember(this);
        }

        this.team = team;

        if(team != null){
            team.addMember(this);
        }
    }
}

