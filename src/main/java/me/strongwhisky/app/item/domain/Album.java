package me.strongwhisky.app.item.domain;

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
@Table(name = "ALBUM")
@DiscriminatorValue(value = "ALBUM")
public class Album extends Item {

    private String artist;

    private String etc;
}