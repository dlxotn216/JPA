package me.strongwhisky.app.day31.document.domain.repository;

import me.strongwhisky.app.day31.document.domain.model.Document;
import me.strongwhisky.app.day31.document.domain.model.specification.DocumentSpecificationBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee Tae Su on 2018-06-12.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DocumentSpecificationRepositoryTest {
	
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

		DocumentSpecificationBuilder builder = new DocumentSpecificationBuilder(criteria);
		Specification<Document> specification = builder.build();
		documentRepository.findAll(specification).forEach(System.out::println);
	}

	@Test
	public void findByEqualTest() {
		String criteria = "name=문서1";

		DocumentSpecificationBuilder builder = new DocumentSpecificationBuilder(criteria);
		Specification<Document> specification = builder.build();
		documentRepository.findAll(specification).forEach(System.out::println);
	}

	@Test
	public void findByNotEqualTest() {
		String criteria = "name!=문서1";

		DocumentSpecificationBuilder builder = new DocumentSpecificationBuilder(criteria);
		Specification<Document> specification = builder.build();
		documentRepository.findAll(specification).forEach(System.out::println);
	}

	@Test
	public void findByGreaterThanTest() {
		String criteria = "documentKey>3";

		DocumentSpecificationBuilder builder = new DocumentSpecificationBuilder(criteria);
		Specification<Document> specification = builder.build();
		documentRepository.findAll(specification).forEach(System.out::println);
	}

	@Test
	public void findByGreaterEqualThanTest() {
		String criteria = "documentKey>=3";

		DocumentSpecificationBuilder builder = new DocumentSpecificationBuilder(criteria);
		Specification<Document> specification = builder.build();
		documentRepository.findAll(specification).forEach(System.out::println);
	}

}