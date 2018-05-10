package me.strongwhisky.app._day18;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by taesu on 2018-05-10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class TodoControllerTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TestRestTemplate restTemplate;

    Todo todo;

    @Before
    public void init() {
        todo = new Todo();
        todo.setTitle("tset1");
        todo.setContent("aefaewf");
        todoService.save(todo);
    }

    @Test
    public void order1_Todd조회테스트() {
        //Given
        //Before init()

        //When
        ResponseEntity<Todo> result = restTemplate.getForEntity("/todos/" + todo.getTodoKey(), Todo.class);

        //Then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getTitle()).isNull();
        assertThat(result.getBody().getContent()).isNull();

    }

    @Test
    public void order2_Todo조회테스트_requestParam을통해() {
        //Given
        //Before init()

        //When
        ResponseEntity<Todo> result = restTemplate.getForEntity("/todos-by-req/?todoKey=" + todo.getTodoKey(), Todo.class);

        //Then

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getTitle()).isNotNull();
        assertThat(result.getBody().getContent()).isNotNull();
        assertThat(result.getBody()).isEqualToComparingOnlyGivenFields(todo,
                "title", "content");
    }

    @Test
    public void order3_PagingTest() throws Exception {
        //Given
        //Before init()

        //When
        ResponseEntity<String> response = restTemplate.getForEntity("/todos", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> result = objectMapper.readValue(response.getBody(), new TypeReference<Map<String,Object>>(){});

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.get("totalElements")).isEqualTo(3);
    }

}