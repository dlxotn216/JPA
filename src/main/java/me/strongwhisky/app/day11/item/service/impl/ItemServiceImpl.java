package me.strongwhisky.app.day11.item.service.impl;

import me.strongwhisky.app.day11.item.exception.ItemNotFoundException;
import me.strongwhisky.app.day11.item.model.Item;
import me.strongwhisky.app.day11.item.repository.ItemRepository;
import me.strongwhisky.app.day11.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by taesu on 2018-05-07.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> saveItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    @Override
    public Item saveItem(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public List<Item> findItems() {
        return this.itemRepository.findAll();
    }

    @Override
    public Page<Item> findItems(Pageable pageable) {
        return this.itemRepository.findAll(pageable);
    }

    @Override
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(ItemNotFoundException::new);
    }
}
