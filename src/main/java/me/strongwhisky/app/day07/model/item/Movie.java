package me.strongwhisky.app.day07.model.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by taesu on 2018-04-26.
 */
@Entity
@Getter
@Setter
@Table(name = "MOVIE")
@DiscriminatorValue(value = "MOVIE")
public class Movie extends Item {

    private String director;

    private String actor;
}
