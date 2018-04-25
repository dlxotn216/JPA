package me.strongwhisky.app.day07.model.member;

import lombok.Data;
import me.strongwhisky.app.day07.model.order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity
@Table(name = "MEMBER")
@Data
public class Member {

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
