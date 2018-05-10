package me.strongwhisky.app._day18;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by taesu on 2018-05-10.
 */
public interface TodoService {
    Todo save(Todo todo);

    Page<Todo> findAll(Pageable pageable);

    Todo findById(Long todoKey);
}
