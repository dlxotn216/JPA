package me.strongwhisky.app.day11.item.service.impl;

import me.strongwhisky.app.day11.item.model.Album;
import me.strongwhisky.app.day11.item.model.Book;
import me.strongwhisky.app.day11.item.model.Movie;
import me.strongwhisky.app.day11.item.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by taesu on 2018-05-07.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void 아이템생성() {
        //Given
        Album album = new Album();
        album.setName("앨범1");
        album.setArtist("Artist1");
        album.setEtc("테스트");

        Book book = new Book();
        book.setName("책1");
        book.setAuthor("이태수");
        book.setIsbn("123-123-234-5235");


        Movie movie = new Movie();
        movie.setName("영화1");
        movie.setDirector("장길수");
        movie.setActor("정우성");

        //When
        itemService.saveItems(Arrays.asList(album, book, movie));

        //Then
        assertThat(itemService.findItems()).isNotEmpty();
        itemService.findItems().forEach(item->{
            System.out.println(item.getItemId()+" : "+item.getName());
        });
    }

}