package me.strongwhisky.app.day06.manytomany.connecentity01;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-23.
 * 다대다 단방향 (연결 엔티티 사용)
 *
 * 연결 엔티티가 연관관계의 주인이 된다
 *
 * 다대다 관계에서 식별 관계이다 (받아온 식별자는 기본기 + 외래키로 사용한다)
 */
@Entity
@Data
@IdClass(MemberProductId.class)     //복합키 선언
public class MemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    int orderAmount;
}
