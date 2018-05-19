package me.strongwhisky.app.day22.domain.repository;

import me.strongwhisky.app.day22.domain.model.User;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * Created by taesu on 2018-05-19.
 */
public interface AdditionalUserRepository {
    List<User> findAllWithUserAllGraph(Pageable pageable);

    Optional<User> findAllWithUserAllGraphByUserKey(Long userKey);
}
