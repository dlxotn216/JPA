package me.strongwhisky.app.day04.mode;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-19.
 *
 * 한 Member는 하나의 Team에만 속할 수 있다.
 * Data 중심 설계에선 Member에서 Team의 PK를 FK로 가질 것이다
 * 즉, Member 모델 안에 teamId를 가질 것이다.
 *
 * 객체지향 설계에선 FK를 가지는 것이아닌 참조 자체를 가진다
 * 즉, Team 객체를 composite 하게 가진다
 */
@Entity
@Table(name = "DAY_04_MEMBER")
@Data
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 한 Member는 하나의 Team에만 할당 될 수 있으므로 ManyToOne
     * JoinColumn엔 Team의 @Id로 지정 된 컬럼의 이름을 적는다
     */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
