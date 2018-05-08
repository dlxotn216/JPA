package me.strongwhisky.app.order.service;

import me.strongwhisky.app.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by taesu on 2018-05-07.
 */
public interface OrderService {
    Order order(Long memberKey, Long itemId, int count);

    void cancel(Long orderId);

    Order findOrderById(Long orderId);

    List<Order> findOrders();

    Page<Order> findOrders(Pageable pageable);
}
