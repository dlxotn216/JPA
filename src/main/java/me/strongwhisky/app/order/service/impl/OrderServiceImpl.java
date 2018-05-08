package me.strongwhisky.app.order.service.impl;

import me.strongwhisky.app.item.domain.Item;
import me.strongwhisky.app.item.service.ItemService;
import me.strongwhisky.app.member.domain.Member;
import me.strongwhisky.app.member.service.MemberService;
import me.strongwhisky.app.order.exception.OrderNotFoundException;
import me.strongwhisky.app.order.domain.Delivery;
import me.strongwhisky.app.order.domain.Order;
import me.strongwhisky.app.order.domain.OrderItem;
import me.strongwhisky.app.order.repository.OrderRepository;
import me.strongwhisky.app.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by taesu on 2018-05-07.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MemberService memberService;

    @Override
    public @Transactional
    Order order(Long memberKey, Long itemId, int count) {
        Member member = memberService.findByKey(memberKey);
        Item item = itemService.findById(itemId);

        Delivery delivery = new Delivery(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        return orderRepository.save(order);
    }

    @Override
    public Order findOrderById(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> findOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public @Transactional
    void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.cancel();

        orderRepository.save(order);
    }
}
