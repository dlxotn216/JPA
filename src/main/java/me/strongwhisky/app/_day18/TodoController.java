package me.strongwhisky.app._day18;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by taesu on 2018-05-10.
 */
@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping(value = "/todos")
    public ResponseEntity<Page<Todo>> getPagedTodos(
            //Pageable의 기본 값을 변경 할 경우
            @PageableDefault(page = 0, size = 10)
            //다중 페이징 옵션을 받아야 할 때
            //todo_page=0&todo_size=20 ...
            @Qualifier("todo") Pageable todoPageable,
            //member_page=0&member_size=20 ...
            @Qualifier("member") Pageable memberPageable){
        return ResponseEntity.ok(todoService.findAll(todoPageable));
    }

    //todoKey에 대한 매핑은 자동으로 이뤄진다
    @GetMapping(value = "/todos/{todoKey}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long todoKey, Todo todo) {
        return ResponseEntity.ok(todo);
    }

    //@EnableSpringDataWebSupport 기능을 ON 한 경우 도메인 클래 컨버터 기능이 동작하여 todo가 조회 됨
    //하지만 클래스 내에서 엔티티는 영속상태가 아니므로 변경 사항이 반영되지 않음
    //-> OSIV 적용 할 경우엔 View 까지 가능하므로 변경 사항이 반영 되기도 함
    @GetMapping(value = "/todos-by-req")
    public ResponseEntity<Todo> getTodos(@RequestParam("todoKey") Todo todo) {
        return ResponseEntity.ok(todo);
    }
}
