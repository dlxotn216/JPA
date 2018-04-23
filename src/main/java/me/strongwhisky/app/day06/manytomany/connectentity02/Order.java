package me.strongwhisky.app.day06.manytomany.connectentity02;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-23.
 * 다대다 단방향 (연결 엔티티 사용)
 * <p>
 * 연결 엔티티가 연관관계의 주인이 된다
 *
 * 별도의 복합키 식별자 클래스를 쓰는 것이 아니라
 * 새로운 기본키를 사용하면 관리도 간편해지고 구현도 간편해진다
 *
 * 다대다 관계에서 비식별 관계이다 (받아온 식별자는 외래키로만 사용하고 새로운 식별자를 추가)
 */
@Entity @Data
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_KEY")
    private Long orderKey;

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
