package me.strongwhisky.app.item.repository;

import me.strongwhisky.app.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by taesu on 2018-05-07.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
