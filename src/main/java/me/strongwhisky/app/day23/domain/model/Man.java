package me.strongwhisky.app.day23.domain.model;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day23.service.Visitor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by taesu on 2018-05-23.
 */
@Getter
@Setter
@Entity
@Table
@DiscriminatorValue(value = "MAN")
public class Man extends Person {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
