package me.strongwhisky.app.item.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by taesu on 2018-04-26.
 */
@Entity
@Getter
@Setter
@Table(name = "BOOK")
@DiscriminatorValue(value = "BOOK")
public class Book extends Item{

    private String author;

    @Column(name = "ISBN", unique = true, nullable = false)
    private String isbn;
}
