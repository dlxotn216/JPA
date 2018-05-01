package me.strongwhisky.app.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by taesu on 2018-05-01.
 */
@Entity
@Table(name = "TEST_TEAM")
@Setter
@Getter
public class TestTeam {
    @Id
    private String teamId;

    private String teamName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<TestMember> members;
}
