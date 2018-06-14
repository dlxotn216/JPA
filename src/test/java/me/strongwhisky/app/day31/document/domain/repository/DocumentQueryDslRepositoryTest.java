package me.strongwhisky.app.day31.document.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import me.strongwhisky.app.day31.document.domain.model.Document;
import me.strongwhisky.app.day31.document.domain.model.predicate.DocumentPredicatesBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by Lee Tae Su on 2018-06-12.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DocumentQueryDslRepositoryTest {
	
	@Autowired
	private DocumentRepository documentRepository;

	@Before
	public void init(){
		documentRepository.saveAll(
				Arrays.asList(
						new Document("문서1", "테스트 aef"),
						new Document("문서2", "13"),
						new Document("문서3", "12"),
						new Document("문서4", "134"),
						new Document("문서5", "22"),
						new Document("문서6", "1")
				)
		);
	}
	
	@Test
	public void findByLikeTest() {
		String criteria = "name:문서";

		DocumentPredicatesBuilder builder = new DocumentPredicatesBuilder(criteria);
		BooleanExpression expression = builder.build();
		documentRepository.findAll(expression).forEach(System.out::println);
	}

	@Test
	public void findByEqualTest() {
		String criteria = "name=문서1";

		DocumentPredicatesBuilder builder = new DocumentPredicatesBuilder(criteria);
		BooleanExpression expression = builder.build();
		documentRepository.findAll(expression).forEach(System.out::println);
	}

	@Test
	public void findByNotEqualTest() {
		String criteria = "name!=문서1";

		DocumentPredicatesBuilder builder = new DocumentPredicatesBuilder(criteria);
		BooleanExpression expression = builder.build();
		documentRepository.findAll(expression).forEach(System.out::println);
	}

	@Test
	public void findByGreaterThanTest() {
		String criteria = "documentKey>3";

		DocumentPredicatesBuilder builder = new DocumentPredicatesBuilder(criteria);
		BooleanExpression expression = builder.build();
		documentRepository.findAll(expression).forEach(System.out::println);
	}

	@Test
	public void findByGreaterEqualThanTest() {
		String criteria = "documentKey>=3";

		DocumentPredicatesBuilder builder = new DocumentPredicatesBuilder(criteria);
		BooleanExpression expression = builder.build();
		documentRepository.findAll(expression).forEach(System.out::println);
	}

}