package me.strongwhisky.app._day19;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-05-16.
 */
@Entity
@Table
@Getter
@Setter
/**
 * BooleanConverter의 Global 설정이 되어있다
 *
 * @see BooleanConverter
 *
 * 아래는 개별적으로 Entity 마다 사용할 때
 * -@Convert(converter = BooleanConverter.class, attributeName = "secret")
 */
public class Board {
    @Id
    @GeneratedValue(generator = "BoardSeq")
    @SequenceGenerator(name = "BoardSeq", sequenceName = "BOARD_SEQ")
    private Long boardId;

    private String title;

    private String content;

    /*
    @OrderColumn(name = "SEQ_NO")
    Comment table에 SEQ_NO 컬럼이 생기며 순번을 저장 함
        -> 리스트에도 순번이 보장 됨
    단 Comment 엔티티엔 해당 프로퍼티가 없기 때문에 관리 불편, 중간이 빌 경우 NPE 발생 가능성 높아짐
        -> @OrderBy를 사용하길 권장
     */

    /**
     * JPA에선 Java Collection 유형에 대해
     * Wrapper를 사용함
     * <p>
     * Collection, List -> PersistentBag
     * Set -> PersistentSet
     * List + @OrderColumn -> PersistentList
     * <p>
     * 이런 특성으로 아래처럼 즉시 초기화를 하는 것을 권장
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @OrderBy("seqNo asc, title asc")
    private List<Comment> comments = new ArrayList<>();

    //OrderBy와 Set을 쓸 경우 내부적으로 LinkedHashSet을 쓴다
//    private Set<Comment> comments = new HashSet<>();

    /**
     * BooleanConverter의 Global 설정이 되어있다
     *
     * @see BooleanConverter
     *
     * 아래는 개별적으로 Attribute 마다 사용할 때
     * -@Convert(converter = BooleanConverter.class)
     */
    private Boolean secret;

    public void addComment(Comment comment) {
        if (!this.comments.contains(comment)) {
            this.comments.add(comment);
        }
        comment.setBoard(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }

    //엔티티 리스너 등록방법 1
    @PrePersist
    public void onPrePersist(){
        System.out.println("CHECK Board onPrePersist");
    }

    @PostPersist
    public void onPostPersist(){
        System.out.println("CHECK Board onPostPersist");
    }

    @PreUpdate
    public void onPreUpdate(){
        System.out.println("CHECK Board onPreUpdate");
    }

    @PostUpdate
    public void onPostUpdate(){
        System.out.println("CHECK Board onPostUpdate");
    }

    @PostLoad
    public void onPostLoad(){
        System.out.println("CHECK Board onPostLoad ");
    }

    @PreRemove
    public void onPreRemove(){
        System.out.println("CHECK Board onPreRemove");
    }

    @PostRemove
    public void onPostRemove(){
        System.out.println("CHECK Board onPostRemove");
    }

}
