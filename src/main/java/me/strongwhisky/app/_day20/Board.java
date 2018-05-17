package me.strongwhisky.app._day20;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BooleanConverter의 Global 설정이 되어있다
 *
 * @see BooleanConverter
 * <p>
 * 아래는 개별적으로 Entity 마다 사용할 때
 * -@Convert(converter = BooleanConverter.class, attributeName = "secret")
 */
@Entity
@Table
@Getter
@Setter
@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "Board.writer",
                attributeNodes = {
                        @NamedAttributeNode(value = "writer")
                }),
        @NamedEntityGraph(
                name = "Board.comments",
                attributeNodes = {
                        @NamedAttributeNode(value = "comments", subgraph = "Board.comments.writer")
                },
                subgraphs = @NamedSubgraph(name = "Board.comments.writer", attributeNodes = {
                    @NamedAttributeNode("writer")   //Comment 클래스의 writer
                })),
        @NamedEntityGraph(
                name = "Board.withAll",
                attributeNodes = {
                        @NamedAttributeNode(value = "writer"),
                        @NamedAttributeNode(value = "comments", subgraph = "Board.comments.writer")
                },
                subgraphs = {
                        @NamedSubgraph(name = "Board.comments.writer", attributeNodes = {
                                @NamedAttributeNode("writer")   //Comment 클래스의 writer
                        })
                }
        )
})
public class Board {
    @Id
    @GeneratedValue(generator = "BoardSeq")
    @SequenceGenerator(name = "BoardSeq", sequenceName = "BOARD_SEQ")
    private Long boardId;

    private String title;

    private String content;

    private Boolean secret;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @OrderBy("seqNo asc")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer writer;

    public void addComment(Comment comment) {
        if (!this.comments.contains(comment)) {
            this.comments.add(comment);
            comment.setBoard(this);
        }
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }
}
