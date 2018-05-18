package me.strongwhisky.app._day21;

import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taesu on 2018-05-19.
 */
@Service
public class DynamicEntityGraphTest {
    @PersistenceContext
    private EntityManager entityManager;

    public void test() {
        //Annotatioin으로 명시하듯이 동적으로
        //EntityGraph와 SubGraph를 추가할 수 있다
        EntityGraph<Board> boardEntityGraph = entityManager.createEntityGraph(Board.class);
        boardEntityGraph.addAttributeNodes("writer");

        boardEntityGraph.addSubgraph("comments").addAttributeNodes("writer");

        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", boardEntityGraph);

        Board board = entityManager.find(Board.class, 1L, hints);
        System.out.println("CHECK findByDynamicEntityGraph boardWithAll :" + board.getTitle());
        System.out.println("CHECK findByDynamicEntityGraph boardWithAll :" + board.getWriter().getName());
        System.out.println("CHECK findByDynamicEntityGraph boardWithAll :" + board.getComments().size());
        System.out.println("CHECK findByDynamicEntityGraph boardWithAll :" + board.getComments().get(0).getWriter().getName());
    }
}
