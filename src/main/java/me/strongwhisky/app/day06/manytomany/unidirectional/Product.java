package me.strongwhisky.app.day06.manytomany.unidirectional;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대다 양방향 매핑
 */
@Entity
@Data
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;
    private String name;

    @ManyToMany(mappedBy = "products")
    private List<Member> members;
}
