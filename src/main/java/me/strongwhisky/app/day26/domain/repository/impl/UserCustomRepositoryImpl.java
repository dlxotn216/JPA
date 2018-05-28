package me.strongwhisky.app.day26.domain.repository.impl;

import me.strongwhisky.app.day26.domain.model.QGroup;
import me.strongwhisky.app.day26.domain.model.QUser;
import me.strongwhisky.app.day26.domain.model.User;
import me.strongwhisky.app.day26.domain.repository.UserCustomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * Created by taesu on 2018-05-28.
 */
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {
    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> findAllByAgeGreaterThanWithAllGraph(int age) {
        QUser qUser = new QUser("q1");
        QGroup qGroup = new QGroup("g1");

        //Fetch join에선 Join on 절 사용 불가 함
        return from(qUser)
                .leftJoin(qUser.group, qGroup)
//                .on(qUser.age.gt(age)) ->  with-clause not allowed on fetched associations; use filters
                .fetchJoin()
                .where(qUser.age.gt(age))
                .fetch();
    }
}
