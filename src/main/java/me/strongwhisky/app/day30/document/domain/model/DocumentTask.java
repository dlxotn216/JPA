package me.strongwhisky.app.day30.document.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-12
 */
@Table(name = "DOCUMENT_TASK")
@Entity
@Getter
@NoArgsConstructor
public class DocumentTask {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocTaskSeqGen")
	@SequenceGenerator(name = "DocTaskSeqGen", sequenceName = "DOC_TASK_SEQ")
	private Long documentTaskKey;
	
	private String name;
	
	private String description;

	@Column(name = "SEQUENCE")
	private Integer order;

	@Enumerated(value = EnumType.STRING)
	private TaskStatus taskStatus;
	
	private Boolean autoStart;
	
	private ZonedDateTime dueAt;
	
	private ZonedDateTime startedAt;
	
	private ZonedDateTime completedAt;
	
	@ManyToOne
	@JoinColumn(name = "DOCUMENT_KEY")
	private Document document;
	
	public static DocumentTask createInitialDocumentTask(Document document,
														 String name,
														 String description,
														 Integer order,
														 Boolean autoStart,
														 ZonedDateTime dueAt) {
		DocumentTask documentTask = new DocumentTask(name, description, order, TaskStatus.WAITING, autoStart, dueAt, document);
		
		document.addDocumenTask(documentTask);
		return documentTask;
	}
	
	private DocumentTask(String name, String description, Integer order, TaskStatus taskStatus, Boolean autoStart, ZonedDateTime dueAt, Document document) {
		this.name = name;
		this.description = description;
		this.order = order;
		this.taskStatus = taskStatus;
		this.autoStart = autoStart;
		this.dueAt = dueAt;
		this.document = document;
	}
	
	public void start() {
		if(isTaskStatusValidToStart()) {
			throw new IllegalStateException("태스크의 상태가 올바르지 않습니다");
		}
		
		if(isTaskExpired()) {
			throw new IllegalArgumentException("만료 된 태스크는 시작할 수 없습니다.");
		}
		
		this.taskStatus = TaskStatus.PROCESSING;
		this.startedAt = ZonedDateTime.now(ZoneOffset.UTC);
	}
	
	private boolean isTaskStatusValidToStart() {
		return this.taskStatus != TaskStatus.WAITING;
	}
	
	public void complete(){
		if(!isTaskStatusValidToComplete()) {
			throw new IllegalStateException("태스크의 상태가 올바르지 않습니다");
		}
		
		if(isTaskExpired()) {
			throw new IllegalArgumentException("만료 된 태스크는 완료 처리할 수 없습니다.");
		}
		
		this.taskStatus = TaskStatus.COMPLETED;
		this.completedAt = ZonedDateTime.now(ZoneId.from(ZoneOffset.UTC));
	}
	
	private boolean isTaskStatusValidToComplete(){
		return this.taskStatus == TaskStatus.PROCESSING;
	}
	
	private boolean isTaskExpired() {
		return this.dueAt != null && ZonedDateTime.now(ZoneOffset.UTC).isAfter(this.dueAt);
	}
	
	public TaskStatus getTaskStatus() {
		if(isTaskExpired()) {
			return TaskStatus.EXPIRED;
		} else {
			return this.taskStatus;
		}
	}
	
	int compareByOrder(DocumentTask documentTask) {
		if(this.order.compareTo(documentTask.order) > 0) {
			return 1;
		} else if(this.order.compareTo(documentTask.order) == 0) {
			return 0;
		} else {
			return -1;
		}
		
	}
}
