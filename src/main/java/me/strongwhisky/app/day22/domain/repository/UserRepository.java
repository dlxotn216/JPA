package me.strongwhisky.app.day22.domain.repository;

import me.strongwhisky.app.day22.domain.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Created by taesu on 2018-05-19.
 */
public interface UserRepository extends JpaRepository<User, Long>,
                                        JpaSpecificationExecutor<User>,
                                        AdditionalUserRepository {

    //JpaRepository 하위 인터페이스에서 NamedEntityGraph 사용 법
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "User.withAll")
    Optional<User> findOneWithDeepGraphByUserKey(Long userKey);
}
