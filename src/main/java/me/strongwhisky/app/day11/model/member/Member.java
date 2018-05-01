package me.strongwhisky.app.day11.model.member;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day11.model.base.BaseEntity;
import me.strongwhisky.app.day11.model.order.Order;
import me.strongwhisky.app.day11.model.value.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity(name = "Member")
@Table(name = "MEMBER")
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }
}
