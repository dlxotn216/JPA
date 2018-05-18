package me.strongwhisky.app.test.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by taesu on 2018-05-19.
 */
@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PermissionKeyGen")
    @SequenceGenerator(name = "PermissionKeyGen", sequenceName = "PERMISSION_SEQ")
    private Long permissionKey;

    private String permissionId;

    private String description;

    private Integer seq;

}
