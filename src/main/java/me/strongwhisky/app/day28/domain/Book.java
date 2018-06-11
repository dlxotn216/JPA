package me.strongwhisky.app.day28.domain;

import lombok.Getter;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-11
 */
@Entity
@Table
@Getter
@EntityListeners(value = {AuditingEntityListener.class})
@Audited(withModifiedFlag = true)
public class Book extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BookSeqGenerator")
	@SequenceGenerator(name = "BookSeqGenerator", sequenceName = "BOOK_SEQ")
	private Long bookKey;
	
	@Column(nullable = false, unique = true)
	private String isbn;
	
	@Column(nullable = false)
	private String title;
	
	private String author;
	
	protected Book() {
	}
	
	public Book(String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
}
