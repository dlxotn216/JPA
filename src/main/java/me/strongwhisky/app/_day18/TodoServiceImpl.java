package me.strongwhisky.app._day18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-05-10.
 */
@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo save(Todo todo){
        return todoRepository.save(todo);
    }

    @Override
    public Page<Todo> findAll(Pageable pageable){
        return todoRepository.findAll(pageable);
    }

    @Override
    public Todo findById(Long todoKey){
        return todoRepository.findById(todoKey).orElse(new Todo());
    }
}
