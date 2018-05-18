package me.strongwhisky.app.test.service;

import me.strongwhisky.app.test.domain.model.User;
import me.strongwhisky.app.test.domain.model.UserRole;
import org.springframework.stereotype.Service;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taesu on 2018-05-19.
 */
@Service
public class FindService {
    @PersistenceContext
    private EntityManager entityManager;

    public void findUserWithAll(){
        System.out.println("========================start of find service =========================");
        EntityGraph userEntityGraph = entityManager.getEntityGraph("User.withAll");

        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", userEntityGraph);

        User user = entityManager.find(User.class, 1L, hints);
        System.out.println(user);


        EntityGraph<User> userEntityGraph1 = entityManager.createEntityGraph(User.class);
        userEntityGraph.addAttributeNodes("userRoles");

        Subgraph<UserRole> userRoleSubgraph = userEntityGraph1.addSubgraph("userRoles");
        userRoleSubgraph.addAttributeNodes("role");

        Map<String, Object> hints2 = new HashMap<>();
        hints2.put("javax.persistence.fetchgraph", userEntityGraph1);

        User user1 = entityManager.find(User.class, 1L, hints2);
        System.out.println(user1);

        //두번째 사용 시 Join하지 않음 주의
        user1 = entityManager.find(User.class, 1L, hints);
        System.out.println(user1);
    }
    /*

    ========================start of find service =========================
    2018-05-19 03:05:36.991 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_1_0_, user0_.email as email2_1_0_, user0_.name as name3_1_0_, user0_.user_id as user_id4_1_0_, userroles1_.user_key as user_key3_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_, role2_.role_key as role_key1_12_3_, role2_.description as descript2_12_3_, role2_.name as name3_12_3_ from app_user user0_ left outer join user_role userroles1_ on user0_.user_key=userroles1_.user_key left outer join role role2_ on userroles1_.role_key=role2_.role_key where user0_.user_key=?
    me.strongwhisky.app.test.domain.model.User@392f0816
    2018-05-19 03:05:37.005 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_1_0_, user0_.email as email2_1_0_, user0_.name as name3_1_0_, user0_.user_id as user_id4_1_0_, userroles1_.user_key as user_key3_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_, role2_.role_key as role_key1_12_3_, role2_.description as descript2_12_3_, role2_.name as name3_12_3_ from app_user user0_ left outer join user_role userroles1_ on user0_.user_key=userroles1_.user_key left outer join role role2_ on userroles1_.role_key=role2_.role_key where user0_.user_key=?
    me.strongwhisky.app.test.domain.model.User@5ada3d63
    2018-05-19 03:05:40.578 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_1_0_, user0_.email as email2_1_0_, user0_.name as name3_1_0_, user0_.user_id as user_id4_1_0_, userroles1_.user_key as user_key3_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_ from app_user user0_ left outer join user_role userroles1_ on user0_.user_key=userroles1_.user_key where user0_.user_key=?
    2018-05-19 03:05:40.585 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select role0_.role_key as role_key1_12_0_, role0_.description as descript2_12_0_, role0_.name as name3_12_0_, userroles1_.role_key as role_key2_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_ from role role0_ left outer join user_role userroles1_ on role0_.role_key=userroles1_.role_key where role0_.role_key=?
    2018-05-19 03:05:40.590 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_1_0_, user0_.email as email2_1_0_, user0_.name as name3_1_0_, user0_.user_id as user_id4_1_0_, userroles1_.user_key as user_key3_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_ from app_user user0_ left outer join user_role userroles1_ on user0_.user_key=userroles1_.user_key where user0_.user_key=?
    2018-05-19 03:05:40.593 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_1_0_, user0_.email as email2_1_0_, user0_.name as name3_1_0_, user0_.user_id as user_id4_1_0_, userroles1_.user_key as user_key3_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_ from app_user user0_ left outer join user_role userroles1_ on user0_.user_key=userroles1_.user_key where user0_.user_key=?
    2018-05-19 03:05:40.597 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select role0_.role_key as role_key1_12_0_, role0_.description as descript2_12_0_, role0_.name as name3_12_0_, userroles1_.role_key as role_key2_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_ from role role0_ left outer join user_role userroles1_ on role0_.role_key=userroles1_.role_key where role0_.role_key=?
    2018-05-19 03:05:40.600 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select user0_.user_key as user_key1_1_0_, user0_.email as email2_1_0_, user0_.name as name3_1_0_, user0_.user_id as user_id4_1_0_, userroles1_.user_key as user_key3_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_ from app_user user0_ left outer join user_role userroles1_ on user0_.user_key=userroles1_.user_key where user0_.user_key=?
    2018-05-19 03:05:40.603 DEBUG 1824 --- [  restartedMain] org.hibernate.SQL                        : select role0_.role_key as role_key1_12_0_, role0_.description as descript2_12_0_, role0_.name as name3_12_0_, userroles1_.role_key as role_key2_14_1_, userroles1_.user_role_key as user_rol1_14_1_, userroles1_.user_role_key as user_rol1_14_2_, userroles1_.role_key as role_key2_14_2_, userroles1_.user_key as user_key3_14_2_ from role role0_ left outer join user_role userroles1_ on role0_.role_key=userroles1_.role_key where role0_.role_key=?
    me.strongwhisky.app.test.domain.model.User@56398a59
    2018-05-19 03:05:42.485 DEBUG 1824 --- [  restartedMain] o.s.boot.devtools.restart.Restarter      : Creating new Restar


     */
}
