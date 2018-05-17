package me.strongwhisky.app._day20;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by taesu on 2018-05-17.
 */
@Service
public class ByUsingJPQLTest {
    @PersistenceContext
    private EntityManager entityManager;


    public void pritnByUsingEntityGraph() {
        //일반적인 JPQL에 hint만 추가
        Board board1 = entityManager.createQuery("select b from Board b where b.boardId = :boardId", Board.class)
                    .setParameter("boardId", 1L)
                    .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("Board.writer"))
                    .getSingleResult();
        System.out.println("CHECK findByJPQL boardWithWriter :" + board1.getTitle());
        System.out.println("CHECK findByJPQL boardWithWriter :" + board1.getWriter().getName());

        Board board2 = entityManager.createQuery("select b from Board b where b.boardId = :boardId", Board.class)
                .setParameter("boardId", 1L)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("Board.withAll"))
                .getSingleResult();
        System.out.println("CHECK findByJPQL boardWithAll :" + board2.getTitle());
        System.out.println("CHECK findByJPQL boardWithAll :" + board2.getWriter().getName());
        System.out.println("CHECK findByJPQL boardWithAll :" + board2.getComments().size());
        System.out.println("CHECK findByJPQL boardWithAll :" + board2.getComments().get(0).getWriter().getName());

    }
}
