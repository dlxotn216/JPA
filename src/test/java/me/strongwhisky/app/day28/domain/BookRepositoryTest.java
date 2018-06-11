package me.strongwhisky.app.day28.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Lee Tae Su on 2018-06-11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest			-> AuditorAware 빈이 등록되지 않아서 AuditEntityListener 내에서 handler를 못 찾아 Auditing 기능이 동작하지 않는다
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void testForDefaultAuditing() {
		Book book = new Book("123-213-123", "test", "lee");
		book = bookRepository.save(book);
		
		Book saved = bookRepository.findById(book.getBookKey()).orElseThrow(IllegalArgumentException::new);
		
		assertThat(saved.getCreatedAt()).isNotNull();
		assertThat(saved.getUpdatedAt()).isNotNull();
		assertThat(saved.getCreatedBy()).isNotNull();
		assertThat(saved.getUpdatedBy()).isNotNull();
	}
	
	@Test
	public void testForAuditTimeZoneIsUTC(){
		Book book = new Book("213-123-85137-23-213-123", "test", "lee");
		book = bookRepository.save(book);
		
		Book saved = bookRepository.findById(book.getBookKey()).orElseThrow(IllegalArgumentException::new);
		
		//normalized를 통해 Zulu 타임과 UTC 타임 규격 맞추기
		assertThat(book.getCreatedAt().getZone().normalized()).isEqualTo(ZoneId.from(ZoneOffset.UTC));
		assertThat(book.getUpdatedAt().getZone().normalized()).isEqualTo(ZoneId.from(ZoneOffset.UTC));
		
		assertThat(saved.getUpdatedAt().getZone().normalized()).isEqualTo(ZoneId.from(ZoneOffset.UTC));
		assertThat(saved.getUpdatedAt().getZone().normalized()).isEqualTo(ZoneId.from(ZoneOffset.UTC));
	}
	
	@Test
	public void testForAuditTimeZoneNotChanged() {
		Book book = new Book("21385137-23-213-123", "test", "lee");
		book = bookRepository.save(book);
		
		Book saved = bookRepository.findById(book.getBookKey()).orElseThrow(IllegalArgumentException::new);
		
		assertThat(book.getCreatedAt()).isEqualTo(saved.getCreatedAt());
		assertThat(book.getUpdatedAt()).isEqualTo(saved.getUpdatedAt());
	}
}