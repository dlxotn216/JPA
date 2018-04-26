package me.strongwhisky.app.day07.model.member;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day07.model.base.BaseEntity;
import me.strongwhisky.app.day07.model.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }
}
