package me.strongwhisky.app.day11.order.model;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day11.common.model.BaseEntity;
import me.strongwhisky.app.day11.item.model.Item;

import javax.persistence.*;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem extends BaseEntity {

    @Id
    @Column(name = "ORDER_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_SEQ")
    @SequenceGenerator(name = "ORDER_ITEM_SEQ")
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Column(name = "ORDER_PRICE")
    private int orderPrice;

    private int count;

    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * count;
    }
}
