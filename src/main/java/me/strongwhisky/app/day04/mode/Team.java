package me.strongwhisky.app.day04.mode;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-19.
 * Team의 반대편인 Member에서 ManyToOne(다대일)을 사용한다
 * 따라서 Member의 반대편인 Team에선 OneToMany(일대다)를 사용하면 된다
 */
@Entity
@Table(name = "DAY_04_TEAM")
@Data
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String teamId;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @OneToMany
    private List<Member> members;

    //Generic이 없다면 아래처럼
//    @OneToMany(targetEntity = Member.class)
//    private List members;

    public void addMember(Member member){
        if(CollectionUtils.isEmpty(members)){
            members = new ArrayList<>();
        }
        members.add(member);
    }
}
