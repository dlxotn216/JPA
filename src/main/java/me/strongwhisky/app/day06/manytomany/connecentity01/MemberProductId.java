package me.strongwhisky.app.day06.manytomany.connecentity01;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대다 단방향 (연결 엔티티 사용 시에 복합 기본키 클래스)
 *
 * Serializable 구현
 * equals, hashCode 메소드 구현
 * default 생성자 필요
 * public 이어야 함
 *
 * -> 굉장히 복잡하고 관리가 귀찮아진다
 */
@Data
@EqualsAndHashCode      //반드시 hash code and equals 오버라이딩
public class MemberProductId implements Serializable {
    private String member;      //Order.member와 연결
    private String product;     //Order.product와 연결
}
