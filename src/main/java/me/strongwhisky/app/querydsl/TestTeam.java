package me.strongwhisky.app.querydsl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-05-01.
 */
@Setter
@Getter
@Entity
@Table
public class TestTeam {
    @Id
    private String teamId;

    private String teamName;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<TestMember> members = new ArrayList<>();
}
