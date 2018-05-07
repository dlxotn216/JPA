package me.strongwhisky.app.day11.order.repository;

import me.strongwhisky.app.day11.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-07.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
