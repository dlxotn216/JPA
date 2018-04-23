package me.strongwhisky.app.day06.manytomany.bidirectional;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대다 단방향
 */
@Entity
@Data
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private String id;
    private String name;
}
