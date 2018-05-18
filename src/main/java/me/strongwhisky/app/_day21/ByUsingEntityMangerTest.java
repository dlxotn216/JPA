package me.strongwhisky.app._day21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taesu on 2018-05-16.
 */
@Service
public class ByUsingEntityMangerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void run() throws Exception {
        Customer customer = new Customer();
        customer.setUserName("taesu@test.com");
        customer.setName("LeeTaeSu");
        customer.setPhone("01099952723");
        customerRepository.save(customer);

        Board board = new Board();
        board.setTitle("test board");
        board.setContent("test  aefaefaf");
        board.setSecret(true);              //비공개용
        board.setWriter(customer);

        Comment comment = new Comment();
        comment.setComment("aefawef");
        comment.setSeqNo(0);
        comment.setWriter(customer);
        board.addComment(comment);

        Board board1 = new Board();
        board1.setTitle("test 11111111111111111");
        board1.setContent("test  21111111111111111");
        board1.setSecret(true);              //비공개용
        board1.setWriter(customer);

        Comment comment1 = new Comment();
        comment1.setComment("2111111111111111111111111");
        comment1.setSeqNo(0);
        comment1.setWriter(customer);
        board1.addComment(comment1);

        boardRepository.saveAll(Arrays.asList(board, board1));

    }

    public void pritnByUsingEntityGraph() {
        EntityGraph boardWithWriter = entityManager.createEntityGraph("Board.writer");
        Map<String, Object> hints1 = new HashMap<>();
        hints1.put("javax.persistence.fetchgraph", boardWithWriter);

        Board board1 = entityManager.find(Board.class, 1L, hints1);
        System.out.println("CHECK findBy boardWithWriter :" + board1.getTitle());
        System.out.println("CHECK findBy boardWithWriter :" + board1.getWriter().getName());
        /*
        select
            board0_.board_id as board_id1_1_0_,
            board0_.content as content2_1_0_,
            board0_.secret as secret3_1_0_,
            board0_.title as title4_1_0_,
            board0_.customer_id as customer5_1_0_,
            customer1_.customer_id as customer1_6_1_,
            customer1_.name as name2_6_1_,
            customer1_.phone as phone3_6_1_,
            customer1_.user_name as user_nam4_6_1_
        from
            board board0_
            left outer join customer customer1_
                on board0_.customer_id=customer1_.customer_id
        where board0_.board_id=?
     */

        EntityGraph boardWithComments = entityManager.createEntityGraph("Board.comments");
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", boardWithComments);

        Board board = entityManager.find(Board.class, 2L, hints);
        System.out.println("CHECK findBy boardWithComments :" + board.getTitle());
        System.out.println("CHECK findBy boardWithComments :" + board.getComments().size());
        System.out.println("CHECK findBy boardWithComments :" + board.getComments().get(0).getWriter().getUserName());
        /*
            아래 옵션일 때
            @NamedEntityGraph(name = "Board.comments", attributeNodes = {
                @NamedAttributeNode(value = "comments")
            })
            select
                board0_.board_id as board_id1_1_0_,
                board0_.content as content2_1_0_,
                board0_.secret as secret3_1_0_,
                board0_.title as title4_1_0_,
                board0_.customer_id as customer5_1_0_,
                comments1_.board_id as board_id4_5_1_,
                comments1_.comment_id as comment_1_5_1_,
                comments1_.comment_id as comment_1_5_2_,
                comments1_.board_id as board_id4_5_2_,
                comments1_.comment as comment2_5_2_,
                comments1_.seq_no as seq_no3_5_2_,
                comments1_.customer_id as customer5_5_2_
            from
                    board board0_
            left outer join comment comments1_
                on board0_.board_id=comments1_.board_id
            where board0_.board_id=? order by comments1_.seq_no asc, comments1_.title asc [42122-197]
         */

        /*
        아래 옵션일 때 (Sub Graph 추가)
             @NamedEntityGraph(
                name = "Board.comments",
                attributeNodes = {
                        @NamedAttributeNode(value = "comments", subgraph = "Board.comments.writer")
                },
                subgraphs = @NamedSubgraph(name = "Board.comments.writer", attributeNodes = {
                    @NamedAttributeNode("writer")   //Comment 클래스의 writer
                }))

            select
                board0_.board_id as board_id1_1_0_,
                board0_.content as content2_1_0_,
                board0_.secret as secret3_1_0_,
                board0_.title as title4_1_0_,
                board0_.customer_id as customer5_1_0_,
                comments1_.board_id as board_id4_5_1_,
                comments1_.comment_id as comment_1_5_1_,
                comments1_.comment_id as comment_1_5_2_,
                comments1_.board_id as board_id4_5_2_,
                comments1_.comment as comment2_5_2_,
                comments1_.seq_no as seq_no3_5_2_,
                comments1_.customer_id as customer5_5_2_,
                customer2_.customer_id as customer1_6_3_,
                customer2_.name as name2_6_3_,
                customer2_.phone as phone3_6_3_,
                customer2_.user_name as user_nam4_6_3_
            from
                board board0_
            left outer join comment comments1_
                    on board0_.board_id=comments1_.board_id
            left outer join customer customer2_
                on comments1_.customer_id=customer2_.customer_id
            where board0_.board_id=?
            order by comments1_.seq_no asc
         */

        EntityGraph boardWithAll = entityManager.createEntityGraph("Board.withAll");
        Map<String, Object> hints2 = new HashMap<>();
        hints2.put("javax.persistence.fetchgraph", boardWithAll);

        Board board2 = entityManager.find(Board.class, 2L, hints2);
        System.out.println("CHECK findBy boardWithAll :" + board2.getTitle());
        System.out.println("CHECK findBy boardWithAll :" + board2.getWriter().getName());
        System.out.println("CHECK findBy boardWithAll :" + board2.getComments().size());
        System.out.println("CHECK findBy boardWithAll :" + board2.getComments().get(0).getWriter().getName());

        /*

        @NamedEntityGraph(
                name = "Board.withAll",
                attributeNodes = {
                        @NamedAttributeNode(value = "writer"),
                        @NamedAttributeNode(value = "comments", subgraph = "Board.comments.writer")
                },
                subgraphs = @NamedSubgraph(name = "Board.comments.writer", attributeNodes = {
                        @NamedAttributeNode("writer")   //Comment 클래스의 writer
                })

        select
            board0_.board_id as board_id1_1_0_,
            board0_.content as content2_1_0_,
            board0_.secret as secret3_1_0_,
            board0_.title as title4_1_0_,
            board0_.customer_id as customer5_1_0_,
            comments1_.board_id as board_id4_5_1_,
            comments1_.comment_id as comment_1_5_1_,
            comments1_.comment_id as comment_1_5_2_,
            comments1_.board_id as board_id4_5_2_,
            comments1_.comment as comment2_5_2_,
            comments1_.seq_no as seq_no3_5_2_,
            comments1_.customer_id as customer5_5_2_,
            customer2_.customer_id as customer1_6_3_,
            customer2_.name as name2_6_3_,
            customer2_.phone as phone3_6_3_,
            customer2_.user_name as user_nam4_6_3_,
            customer3_.customer_id as customer1_6_4_,
            customer3_.name as name2_6_4_,
            customer3_.phone as phone3_6_4_,
            customer3_.user_name as user_nam4_6_4_
        from board board0_
            left outer join comment comments1_
                on board0_.board_id=comments1_.board_id
            left outer join customer customer2_
                on comments1_.customer_id=customer2_.customer_id
            left outer join customer customer3_
                on board0_.customer_id=customer3_.customer_id
        where board0_.board_id=?
        order by comments1_.seq_no asc

         */
    }
}