package me.strongwhisky.app.test.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by taesu on 2018-05-19.
 */
//Depth는 최대 3Depth까지 가능
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "User.withAll",
                attributeNodes = {
                        @NamedAttributeNode(value = "userRoles", subgraph = "User.userRoles")
                },
                subgraphs = {
                        @NamedSubgraph(name = "User.userRoles", attributeNodes = {
                                @NamedAttributeNode("role")
                        })
                }
        )
})

@Entity
@Table(name = "APP_USER")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserKeyGen")
    @SequenceGenerator(name = "UserKeyGen", sequenceName = "USER_SEQ")
    private Long userKey;

    @Column(unique = true)
    private String userId;

    private String name;

    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default        //Lombok의 Builder 사용 시 즉시 초기화 구문이 항상 null임 주의 (Builder.Default annotation 사용)
    private Set<UserRole> userRoles = new HashSet<>();

    public void addUserRole(Role role) {
        this.userRoles.add(UserRole.builder().user(this).role(role).build());
        role.addRoleUser(this);
    }

}
