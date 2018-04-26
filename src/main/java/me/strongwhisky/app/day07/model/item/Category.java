package me.strongwhisky.app.day07.model.item;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day07.model.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-25.
 */
@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category extends BaseEntity {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    @SequenceGenerator(name = "CATEGORY_SEQ")
    private Long categoryId;

//    private Long parentId;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    //이 컬럼을 통해서 Category 엔티티와 연관관계를 맺겠다 (즉, PARENT_ID 는 현재 엔티티의 외래키가 되며 참조 대상의 PK와 자동 매핑 된다)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    private String name;

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM"
            , joinColumns = {@JoinColumn(name = "CATEGORY_ID")}
            , inverseJoinColumns = {@JoinColumn(name = "ITEM_ID")})
    private List<Item> items = new ArrayList<>();

    public void addChild(Category category) {
        this.children.add(category);
        category.setParent(this);
    }

    public void addItem(Item item) {
        this.items.add(item);
        item.addCategory(this);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        item.removeCategory(this);
    }
}
