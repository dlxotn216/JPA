package me.strongwhisky.app.day06.manytoone.unidirectional;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Created by taesu on 2018-04-23.
 *
 * 다대일 양방향 관계에서 Team
 *
 * 반대 방향인 Member가 FK를 가지므로 ManyToOne 이면 Team은 OneToMany이다
 * 항상 연관 관계의 주인이 아닌 쪽은 읽기만 가능하다 (FK에 대해)
 */
@Entity @Data
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String teamId;

    private String teamName;

    //단방향이므로 Member 관련 필드가 없다
}
