package me.strongwhisky.app.test.service;

import me.strongwhisky.app.test.domain.model.Permission;
import me.strongwhisky.app.test.domain.model.Role;
import me.strongwhisky.app.test.domain.model.User;
import me.strongwhisky.app.test.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taesu on 2018-05-19.
 */
@Service
public class InitService {
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;
    private RolePermissionRepository rolePermissionRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public InitService(PermissionRepository permissionRepository, RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public void init() {
        permissionRepository.saveAll(Arrays.asList(
                Permission.builder().permissionId("CREATE").seq(0).description("생성권한").build(),
                Permission.builder().permissionId("READ").seq(1).description("읽기권한").build(),
                Permission.builder().permissionId("UPDATE").seq(2).description("수정권한").build(),
                Permission.builder().permissionId("DELETE").seq(3).description("삭제권한").build()
        ));

        List<Permission> permissions = permissionRepository.findAll(new Sort(Sort.Direction.ASC, "seq"));

        Role role1 = Role.builder().name("ADMIN").description("관리자").build();
        role1.addRolePermission(permissions.get(0));
        role1.addRolePermission(permissions.get(1));
        role1.addRolePermission(permissions.get(2));
        role1.addRolePermission(permissions.get(3));

        Role role2 = Role.builder().name("ANONYMOUS").description("익명사용자").build();
        role2.addRolePermission(permissions.get(0));

        Role role3 = Role.builder().name("DBA").description("Database Admin").build();
        role3.addRolePermission(permissions.get(0));
        role3.addRolePermission(permissions.get(1));
        role3.addRolePermission(permissions.get(3));

        roleRepository.saveAll(Arrays.asList(role1, role2, role3));

        User user1 = User.builder().userId("taesu@test.com").name("LeeTaeSu").email("taesu@test.com").build();
        user1.addUserRole(role1);
        user1.addUserRole(role2);
        user1.addUserRole(role3);

        User user2 = User.builder().userId("test123@test.com").name("test123").email("test123@test.com").build();
        user2.addUserRole(role1);

        User user3 = User.builder().userId("NoMad").name("MadPeople").email("nomad@te213st.com").build();
        user3.addUserRole(role1);

        User user4 = User.builder().userId("Nova").name("novaYou").email("you@tube.com").build();
        user4.addUserRole(role2);

        User user5 = User.builder().userId("Kim").name("Kim").email("kim@bab.com").build();


        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}
