package me.strongwhisky.app.item.service;

import me.strongwhisky.app.item.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by taesu on 2018-05-07.
 */
public interface ItemService {
    List<Item> saveItems(List<Item> items);

    Item saveItem(Item item);

    List<Item> findItems();

    Page<Item> findItems(Pageable pageable);

    Item findById(Long itemId);
}