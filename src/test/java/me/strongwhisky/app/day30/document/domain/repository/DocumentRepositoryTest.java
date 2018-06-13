package me.strongwhisky.app.day30.document.domain.repository;

import me.strongwhisky.app.day30.document.domain.model.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee Tae Su on 2018-06-12.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class DocumentRepositoryTest {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Test
	public void createDocumentAndVersion() {
		//Given
		Document document = new Document("문서1", "설명1");
		document.createDocumentVersion("1.0");
		
		//When
		document = documentRepository.save(document);
		Document saved = documentRepository.findById(document.getDocumentKey()).orElseThrow(IllegalArgumentException::new);
		
		//Then
		assertThat(saved.getDocumentVersions()).isNotEmpty();
		assertThat(saved.getLatestDocumentVersion().getVersion()).isEqualTo("1.0");
		assertThat(saved.getLatestDocumentVersion().getSequence()).isEqualTo(1);
	}
	
	@Test
	public void createDocumentAndManyVersion() {
		//Given
		Document document = new Document("문서1", "설명1");
		document.createDocumentVersion("1.0");
		document.createDocumentVersion("1.0");
		document.createDocumentVersion("1.1");
		
		//When
		document = documentRepository.save(document);
		Document saved = documentRepository.findById(document.getDocumentKey()).orElseThrow(IllegalArgumentException::new);
		
		//Then
		assertThat(saved.getDocumentVersions()).hasSize(3);
		
		assertThat(saved.getDocumentVersions().stream()
				.filter(documentVersion ->
						documentVersion.getVersion().equals("1.0") && documentVersion.getSequence().equals(1)))
				.isNotEmpty();
		
		assertThat(saved.getDocumentVersions().stream()
				.filter(documentVersion ->
						documentVersion.getVersion().equals("1.0") && documentVersion.getSequence().equals(2)))
				.isNotEmpty();
		
		
		assertThat(saved.getLatestDocumentVersion().getVersion()).isEqualTo("1.1");
		assertThat(saved.getLatestDocumentVersion().getSequence()).isEqualTo(1);
	}
}