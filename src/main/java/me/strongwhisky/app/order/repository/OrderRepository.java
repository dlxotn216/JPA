package me.strongwhisky.app.order.repository;

import me.strongwhisky.app.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-07.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
