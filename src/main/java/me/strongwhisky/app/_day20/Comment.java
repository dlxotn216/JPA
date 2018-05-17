package me.strongwhisky.app._day20;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-16.
 */
@Entity
@Table
@Getter
@Setter
@NamedEntityGraph(name = "Comment.writer", attributeNodes = {
        @NamedAttributeNode(value = "writer")
})
public class Comment {

    @Id
    @GeneratedValue(generator = "CommentSeq")
    @SequenceGenerator(name = "CommentSeq", sequenceName = "COMMENT_SEQ")
    private Long commentId;

    private String comment;

    private int seqNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer writer;

    public void setBoard(Board board){
        if(this.board != null){
            this.board.removeComment(this);
        }
        this.board = board;
    }
}
