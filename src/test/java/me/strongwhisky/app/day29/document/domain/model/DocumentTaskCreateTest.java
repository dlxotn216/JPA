package me.strongwhisky.app.day29.document.domain.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Created by Lee Tae Su on 2018-06-12.
 */
@RunWith(SpringRunner.class)
public class DocumentTaskCreateTest {
	
	@Test
	public void createDocumentAndTask() {
		//Given
		Document document = new Document("문서1", "설명1");
		
		//When
		document.createDocumentTask("작성태스크", "작성", 0, false, null);
		
		//Then
		assertThat(document.getDocumentTasks()).isNotEmpty();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createDocumentAndSameOrderedTask() {
		//Given
		Document document = new Document("문서1", "설명1");
		
		//When
		document.createDocumentTask("작성태스크1", "작성", 1, false, null);
		document.createDocumentTask("작성태스크2", "작성", 1, false, null);
		
		//Then
		fail("동일한 순서를 가진 태스크가 생성 됨");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createExpiredTask() {
		//Given
		Document document = new Document("문서1", "설명1");
		ZonedDateTime beforeDate
				= ZonedDateTime.of(2014, 12, 1, 1, 1, 1, 1, ZoneId.from(ZoneOffset.UTC));
		
		//When
		document.createDocumentTask("작성태스크1", "작성", 1, false, beforeDate);
		
		//Then
		fail("만료된 태스크가 생성 됨");
	}
	
	@Test
	public void startWorkflowTest() {
		//Given
		Document document = new Document("문서1", "설명1");
		document.createDocumentTask("작성태스크1", "작성", 1, false, null);
		
		//When
		document.startNextTask();
		
		//Then
		assertThat(document.getCurrentTask().orElseThrow(NullPointerException::new)).isNotNull();
		assertThat(document.getCurrentTask().orElseThrow(NullPointerException::new).getTaskStatus()).isEqualTo(TaskStatus.PROCESSING);
	}
	
	@Test
	public void completeCurrentTaskAndAutoStartNextTaskTest() {
		//Given
		Document document = new Document("문서1", "설명1");
		document.createDocumentTask("작성태스크1", "작성", 1, false, null);
		document.createDocumentTask("작성태스크2", "작성", 2, true, null);
		document.startNextTask();
		
		//When
		document.completeCurrentTask();
		
		//Then
		assertThat(document.getCurrentTask().orElseThrow(NullPointerException::new)).isNotNull();
		assertThat(document.getCurrentTask().orElseThrow(NullPointerException::new).getName()).isEqualTo("작성태스크2");
		assertThat(document.getCurrentTask().orElseThrow(NullPointerException::new).getTaskStatus()).isEqualTo(TaskStatus.PROCESSING);
	}
}