package me.strongwhisky.app.day06.manytomany.connecentity01;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대다 단방향 (연결 엔티티 사용)
 */
@Entity
@Data
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;
    private String name;

    //상품 엔티티에서 회원-상품 엔티티로 객체 그래프 탐색이 필요 없기에 연관관계를 굳이 만들지 않았다
//    @OneToMany(mappedBy = "products")
//    private List<Order> memberProducts = new ArrayList<>();
}
