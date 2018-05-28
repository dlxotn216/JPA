package me.strongwhisky.app.day26.domain.repository;

import me.strongwhisky.app.day26.domain.model.User;

import java.util.List;

/**
 * Created by taesu on 2018-05-28.
 */
public interface UserCustomRepository {
    List<User> findAllByAgeGreaterThanWithAllGraph(int age);
}
