package me.strongwhisky.app.day23.domain.model;

import lombok.Getter;
import lombok.Setter;
import me.strongwhisky.app.day23.service.Visitor;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-23.
 */
@Getter
@Setter
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Person {

    @Id
    private Long personKey;

    private String name;

    private int age;

    public abstract void accept(Visitor visitor);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (personKey != null ? !personKey.equals(person.personKey) : person.personKey != null) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        int result = personKey != null ? personKey.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
