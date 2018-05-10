package me.strongwhisky.app._day18;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-10.
 */
@Entity
@Table
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TodoSeqGen")
    @SequenceGenerator(name = "TodoSeqGen", sequenceName = "TODO_SEQ")
    private Long todoKey;

    private String title;

    private String content;
}
