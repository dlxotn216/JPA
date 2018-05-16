package me.strongwhisky.app._day19;

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
@EntityListeners(value = {CommentListener.class})   //엔티티 리스너 등록 방법 2
public class Comment {

    @Id
    @GeneratedValue(generator = "CommentSeq")
    @SequenceGenerator(name = "CommentSeq", sequenceName = "COMMENT_SEQ")
    private Long commentId;

    private String comment;

    private int seqNo;

    @ManyToOne
    @JoinColumn(name = "board")
    private Board board;

    public void setBoard(Board board){
        if(this.board != null){
            this.board.removeComment(this);
        }
        this.board = board;
    }
}
