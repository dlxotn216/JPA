package me.strongwhisky.app.day32.document.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.strongwhisky.app.day32.document.domain.events.DocumentTaskCompleteEvent;
import me.strongwhisky.app.day32.document.domain.events.DocumentTaskCreateEvent;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-12
 */
@Table(name = "DOCUMENT")
@Entity
@Getter
@NoArgsConstructor
public class Document extends AbstractAggregateRoot {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocSeqGen")
	@SequenceGenerator(name = "DocSeqGen", sequenceName = "DOC_SEQ")
	private Long documentKey;
	
	private String name;
	
	private String description;

	
	@OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("version DESC, sequence DESC")
	private List<DocumentVersion> documentVersions = new ArrayList<>();
	
	@OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DocumentTask> documentTasks = new ArrayList<>();
	
	public Document(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void addDocumentVersion(DocumentVersion documentVersion) {
		this.documentVersions.add(documentVersion);
	}
	
	public void createDocumentVersion(String version) {
		Optional<DocumentVersion> documentVersion = this.getMatchedDocumentVersion(version);
		
		if(documentVersion.isPresent()) {
			DocumentVersion.createNextVersion(this, documentVersion.get());
		} else {
			DocumentVersion.createInitialVersion(this, version);
		}
	}
	
	private Optional<DocumentVersion> getMatchedDocumentVersion(String version) {
		return this.documentVersions.stream()
				.filter(documentVersion -> documentVersion.getVersion().equals(version))
				.findFirst();
	}
	
	public DocumentVersion getLatestDocumentVersion() {
		return this.documentVersions.stream().max(DocumentVersion::compareTo).orElseGet(null);
	}
	
	public void addDocumenTask(DocumentTask documentTask) {
		this.documentTasks.add(documentTask);
	}
	
	public void createDocumentTask(String name, String description, Integer order, Boolean autoStart, ZonedDateTime dueAt) {
		if(this.documentTasks.stream().anyMatch(documentTask -> documentTask.getOrder().equals(order))) {
			throw new IllegalArgumentException("동일한 순서의 order를 가지는 Task는 생성 불가합니다");
		}
		
		if(dueAt != null && ZonedDateTime.now(ZoneOffset.UTC).isAfter(dueAt)) {
			throw new IllegalArgumentException("태스크의 만료 기한은 과거가 될 수 없습니다");
		}

		DocumentTask.createInitialDocumentTask(this, name, description, order, autoStart, dueAt);
		super.registerEvent(new DocumentTaskCreateEvent(this));
	}
	
	public Document completeCurrentTask(){
		getCurrentTask().orElseThrow(NullPointerException::new).complete();
		getNextTask().ifPresent(nextTask->{
			if(nextTask.getAutoStart()){
				nextTask.start();
			}
		});
		super.registerEvent(new DocumentTaskCompleteEvent(this));
		return this;
	}
	
	public void startNextTask() {
		this.getNextTask().ifPresent(DocumentTask::start);
	}
	
	public Optional<DocumentTask> getCurrentTask() {
		return this.documentTasks.stream()
				.filter(task -> task.getTaskStatus() == TaskStatus.PROCESSING)
				.findFirst();
	}
	
	public Optional<DocumentTask> getNextTask() {
		return this.documentTasks.stream()
				.filter(task -> task.getTaskStatus() == TaskStatus.WAITING)
				.min(DocumentTask::compareByOrder);
	}

	@Override
	public String toString() {
		return "Document{" +
				"documentKey='" + documentKey + '\'' +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
