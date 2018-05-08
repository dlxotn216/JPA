package me.strongwhisky.app.day11.order.service.impl;

import me.strongwhisky.app.item.exception.NotEnoughStockException;
import me.strongwhisky.app.item.domain.Book;
import me.strongwhisky.app.item.domain.Item;
import me.strongwhisky.app.item.service.ItemService;
import me.strongwhisky.app.member.domain.Member;
import me.strongwhisky.app.member.service.MemberService;
import me.strongwhisky.app.order.domain.Order;
import me.strongwhisky.app.order.domain.OrderStatus;
import me.strongwhisky.app.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Created by taesu on 2018-05-07.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @Test
    public void 주문() {
        //Given
        Member joinedMember = memberService.join(getMember());

        Book book = getBook(30);
        itemService.saveItem(book);

        //When
        Order ordered = orderService.order(joinedMember.getMemberId(), book.getItemId(), 3);

        //Then
        assertThat(ordered.getStatus()).isEqualTo(OrderStatus.ORDER).describedAs("주문 상태");
        assertThat(ordered.getOrderItems().size()).isEqualTo(1).describedAs("주문한 상품 개수");
        assertThat(ordered.getTotalPrice()).isEqualTo(book.getPrice() * 3).describedAs("총 주문 가격");
        assertThat(book.getStockQuantity()).isEqualTo(27).describedAs("3개 주문으로 인해 재고가 30개에서 27개로 변경");
    }

    @Test(expected = NotEnoughStockException.class)
    public void 수량초과주문() {
        //Given
        Member joinedMember = memberService.join(getMember());

        Book book = getBook(1);
        Item savedItem = itemService.saveItem(book);

        //When
        orderService.order(joinedMember.getMemberId(), savedItem.getItemId(), 3);

        //Then
        fail("초과 주문 할 수 없다");
    }

    @Test
    public void 주문취소(){
        //Given
        Member joinedMember = memberService.join(getMember());

        Book book = getBook(30);
        Item savedItem = itemService.saveItem(book);

        Order ordered = orderService.order(joinedMember.getMemberId(), savedItem.getItemId(), 3);

        //When
        orderService.cancel(ordered.getOrderId());

        //Then
        Order result = orderService.findOrderById(ordered.getOrderId());
        assertThat(result.getStatus()).isEqualTo(OrderStatus.CANCEL).describedAs("취소시 상태 변경");
        assertThat(book.getStockQuantity()).isEqualTo(30).describedAs("취소 시 재고 증가");

    }

    private Member getMember(){
        Member member = new Member();
        member.setUserId("taesu@crscube.co.kr");
        member.setName("Lee Tae Su");
        return member;
    }

    private Book getBook(int stockQuantity){
        Book book = new Book();
        book.setIsbn("123-213-213123-");
        book.setAuthor("Lee");
        book.setName("책1");
        book.setStockQuantity(stockQuantity);
        return book;
    }
}