package me.strongwhisky.app.day28.domain;

import lombok.Getter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-11
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited(withModifiedFlag = true)
public abstract class BaseEntity {
	
	@Column(nullable = false)
	private ZonedDateTime createdAt;
	
	@Column(nullable = false)
	private ZonedDateTime updatedAt;
	
	@Column(nullable = false)
	@CreatedBy
	private String createdBy;
	
	@Column(nullable = false)
	@LastModifiedBy
	private String updatedBy;
	
	
	//현재 Jpa의 Auditing 기능으로 ZonedDateTime이 지원되지 않음
	/*
		org.springframework.dao.InvalidDataAccessApiUsageException: 
			Invalid date type class me.strongwhisky.app.day28.domain.Book for member me.strongwhisky.app.day28.domain.Book@28269c65!
		Supported types are [org.joda.time.DateTime, org.joda.time.LocalDateTime, java.util.Date, java.lang.Long, long].;
	 */
	@PrePersist
	public void onPrePersist() {
		this.createdAt = ZonedDateTime.now(ZoneId.from(ZoneOffset.UTC));
		this.updatedAt = this.createdAt;
	}
	
	@PreUpdate
	public void onPreUpdate() {
		this.updatedAt = ZonedDateTime.now(ZoneId.from(ZoneOffset.UTC));
	}
}
