package me.strongwhisky.app.item.domain;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.common.domain.BaseEntity;
import me.strongwhisky.app.item.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-25.
 * <p>
 * 상속관계 매핑 방법 중 Join 전략을 사용
 * <p>
 * Join 방법은 ITEM Table에 PK와 타입 컬럼을 보유하고
 * 자식 테이블에 각 유형 별 필요 컬럼 보유
 * -> 전체를 한꺼번에 보여줄 때 편리함 (UNION 필요 없음)
 * -> ITEM을 직접적으로 생성해서 사용할 일이 없으므로 abstract 선언
 * -> DiscriminatorColumn을 사용하지 않아도 동작 함
 * <p>
 * InheritanceType.SINGLE_TABLE
 * -> 하나의 테이블에 모두 데이터를 모음
 * -> 아이템 타입 별 사용하지 않는 컬럼은 null로 채워질 수 있고 많은 데이터가 쌓일 수 있음
 * -> DiscriminatorColumn을 반드시 선언해야 함
 * <p>
 * InheritanceTyp.TABLE_PER_CLASS
 * -> 각 테이블 별로 독립적인 PK를 가짐
 * -> 자식 테이블의 통합 쿼리가 어려움
 * -> DB 적 및 ORM 적 관점에서 모두 비추천
 */
@Entity
@Table(name = "ITEM")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ITEM_TYPE")
//@DiscriminatorColumn == @DiscriminatorColumn(name = "DTYPE")
public abstract class Item extends BaseEntity {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
    @SequenceGenerator(name = "ITEM_SEQ")
    private Long itemId;

    private String name;

    private int price;

    @Column(name = "STOCK_QUANTITY")
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
