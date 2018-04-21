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

    //연관관계 편의 메소드 -> 연관관계의 주인인 엔티티에 작성한다
    public void setTeam(Team team){
        //이전 연관관계 제거
        if(this.team != null){
            this.team.removeMember(this);
        }
        this.team = team;
        team.addMember(this);
    }
}
