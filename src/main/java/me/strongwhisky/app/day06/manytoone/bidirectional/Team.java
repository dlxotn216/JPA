package me.strongwhisky.app.day06.manytoone.bidirectional;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-04-23.
 *
 * 다대일 양방향 관계에서 Team
 *
 * 반대 방향인 Member가 FK를 가지므로 ManyToOne 이면 Team은 OneToMany이다
 * 항상 연관 관계의 주인이 아닌 쪽은 읽기만 가능하다 (FK에 대해)
 */
@Entity @Data
@ToString(exclude = "members")
@EqualsAndHashCode(exclude = "members")
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String teamId;

    private String teamName;

    //List, Map, Set 사용 가능
    @OneToMany(mappedBy = "team")
    private List<Member> members;

    public void addMember(Member member){
        if(CollectionUtils.isEmpty(members)){
            members = new ArrayList<>();
        }
        if(!members.contains(member)){      //무한루프 주의
            members.add(member);
            member.setTeam(this);
        }
    }

    public void removeMember(Member member){
        if(CollectionUtils.isEmpty(members)){
            return;
        }
        members.remove(member);
        member.setTeam(null);
    }
}
