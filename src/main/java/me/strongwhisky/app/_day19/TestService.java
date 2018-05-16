package me.strongwhisky.app._day19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by taesu on 2018-05-16.
 */
@Service
public class TestService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void run() throws Exception {
        Board board = new Board();
        board.setTitle("test board");
        board.setContent("test  aefaefaf");
        board.setSecret(true);              //비공개용

        Comment comment = new Comment();
        comment.setComment("aefawef");
        comment.setSeqNo(0);

        board.addComment(comment);

        boardRepository.save(board);
        print();

        board.setContent("Changed board content");
        comment.setComment("Change comment");

        boardRepository.save(board);
        print();

        boardRepository.delete(board);
        print();
    }

    private void print() {
        boardRepository.findAll().forEach(item -> {
            System.out.println(item.getTitle());
            System.out.println(item.getContent());
            item.getComments().forEach(subItem -> {
                System.out.println(subItem.getSeqNo() + ":" + subItem.getComment());
            });
        });

    }

    /*

    CHECK Board onPrePersist
    2018-05-16 21:00:05.276 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : call next value for board_seq
    2018-05-16 21:00:05.284 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : call next value for board_seq
    CHECK Comment onPrePersist
    2018-05-16 21:00:05.308 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : call next value for comment_seq
    2018-05-16 21:00:05.309 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : call next value for comment_seq
    2018-05-16 21:00:05.326  INFO 3648 --- [  restartedMain] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
    2018-05-16 21:00:05.417 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : insert into board (content, secret, title, board_id) values (?, ?, ?, ?)
    CHECK Board onPostPersist
    2018-05-16 21:00:05.423 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : insert into comment (board, comment, seq_no, comment_id) values (?, ?, ?, ?)
    CHECK Comment onPostPersist
    2018-05-16 21:00:05.427 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : select board0_.board_id as board_id1_1_, board0_.content as content2_1_, board0_.secret as secret3_1_, board0_.title as title4_1_ from board board0_
    test board
    test  aefaefaf
    0:aefawef
    CHECK Board onPreUpdate
    CHECK Comment onPreUpdate
    2018-05-16 21:00:05.439 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : update board set content=?, secret=?, title=? where board_id=?
    CHECK Board onPostUpdate
    2018-05-16 21:00:05.442 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : update comment set board=?, comment=?, seq_no=? where comment_id=?
    CHECK Comment onPostUpdate
    2018-05-16 21:00:05.442 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : select board0_.board_id as board_id1_1_, board0_.content as content2_1_, board0_.secret as secret3_1_, board0_.title as title4_1_ from board board0_
    test board
    Changed board content
    0:Change comment
    CHECK Board onPreRemove
    CHECK Comment onPreRemove
    2018-05-16 21:00:05.455 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : delete from comment where comment_id=?
    CHECK Comment onPostRemove
    2018-05-16 21:00:05.458 DEBUG 3648 --- [  restartedMain] org.hibernate.SQL                        : delete from board where board_id=?
    CHECK Board onPostRemove



     */
}
