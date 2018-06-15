package me.strongwhisky.app.day32.document.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-12
 */
@Table(name = "DOCUMENT_VERSION")
@Entity
@Getter
@NoArgsConstructor
public class DocumentVersion implements Comparable<DocumentVersion> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocVerSeqGen")
	@SequenceGenerator(name = "DocVerSeqGen", sequenceName = "DOC_VER_SEQ")
	private Long documentVersionKey;
	
	private String version;
	
	private Integer sequence;
	
	@ManyToOne
	@JoinColumn(name = "DOCUMENT_KEY")
	private Document document;
	
	public static DocumentVersion createInitialVersion(Document document, String version) {
		DocumentVersion initialVersion = new DocumentVersion();
		initialVersion.setVersion(version);
		initialVersion.setSequence(1);
		
		document.addDocumentVersion(initialVersion);
		return initialVersion;
	}
	
	public static DocumentVersion createNextVersion(Document document, DocumentVersion origin) {
		Assert.notNull(origin, "기존 버전은 null이 아니어야 합니다");
		
		DocumentVersion newVersion = new DocumentVersion();
		newVersion.setVersion(origin.getVersion());
		newVersion.setSequence(origin.getSequence() + 1);
		
		document.addDocumentVersion(newVersion);
		return newVersion;
	}
	
	private void setVersion(String version) {
		this.version = version;
	}
	
	private void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	@Override
	public int compareTo(DocumentVersion o) {
		if(this.getVersion().compareTo(o.getVersion()) > 0) {
			return 1;
		} else if(this.getVersion().compareTo(o.getVersion()) == 0) {
			if(this.getSequence().compareTo(o.getSequence()) > 0) {
				return 1;
			} else if(this.getSequence().compareTo(o.getSequence()) == 0) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}
}
