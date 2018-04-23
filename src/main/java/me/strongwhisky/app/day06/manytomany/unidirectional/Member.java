package me.strongwhisky.app.day06.manytomany.unidirectional;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대다 단방향 매핑, 연관관계의 주인 엔티티
 */
@Entity
@Data
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String userName;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT"      //다대다 관계를 일대다, 다대일 관계로 풀기 위핸 연결 테이블로 ManyToMany로 매핑하였기 때문에 별도로 엔티티로 뽑아내지 않아도 괜찮다
            , joinColumns = @JoinColumn(name = "MEMBER_ID")
            , inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product> products = new ArrayList<>();

    //하지만 연결 테이블에 FK 정보만 있는 것이 아니라 별도의 데이터가 들어가야 한다면?
    //MEMBER_PRODUCT 테이블 내에 수량, 일시 등 정보가 추가적으로 필요하다면 ?

}
