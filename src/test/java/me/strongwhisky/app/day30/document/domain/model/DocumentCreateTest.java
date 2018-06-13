package me.strongwhisky.app.day30.document.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee Tae Su on 2018-06-12.
 */
@RunWith(SpringRunner.class)
public class DocumentCreateTest {
	
	@Test
	public void createDocumentAndVersion() {
		//Given
		Document document = new Document("문서1", "설명1");
		
		//When
		document.createDocumentVersion("1.0");
		
		//Then
		assertThat(document.getDocumentVersions()).isNotEmpty();
		assertThat(document.getLatestDocumentVersion().getVersion()).isEqualTo("1.0");
		assertThat(document.getLatestDocumentVersion().getSequence()).isEqualTo(1);
	}
	
	@Test
	public void createDocumentAndManyVersion() {
		//Given
		Document document = new Document("문서1", "설명1");
		
		//When
		document.createDocumentVersion("1.0");
		document.createDocumentVersion("1.0");
		document.createDocumentVersion("1.1");
		
		//Then
		assertThat(document.getDocumentVersions()).hasSize(3);
		
		assertThat(document.getDocumentVersions().get(0).getVersion()).isEqualTo("1.0");
		assertThat(document.getDocumentVersions().get(0).getSequence()).isEqualTo(1);
		
		assertThat(document.getDocumentVersions().get(1).getVersion()).isEqualTo("1.0");
		assertThat(document.getDocumentVersions().get(1).getSequence()).isEqualTo(2);
		
		assertThat(document.getLatestDocumentVersion().getVersion()).isEqualTo("1.1");
		assertThat(document.getLatestDocumentVersion().getSequence()).isEqualTo(1);
	}
}