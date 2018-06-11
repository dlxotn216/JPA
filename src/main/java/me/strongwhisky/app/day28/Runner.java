package me.strongwhisky.app.day28;

import lombok.extern.slf4j.Slf4j;
import me.strongwhisky.app.day28.domain.Book;
import me.strongwhisky.app.day28.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.*;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-11
 */
@Slf4j
@Component
public class Runner implements ApplicationRunner {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		testForAuditing();
		testForJavaTimeApi();
	}
	
	private void testForAuditing() {
		log.info("===============================testForAuditing========================================");
		Book book = new Book("ewtewtwtewt-123-213-123", "test", "lee");
		book = bookRepository.save(book);
		
		log.info("Check Before save " + book.getCreatedAt());
		log.info("Check Before save " + book.getUpdatedAt());
		
		Book saved = bookRepository.findById(book.getBookKey()).orElseThrow(IllegalArgumentException::new);
		
		log.info("Check After save " + saved.getCreatedAt());
		log.info("Check After save " + saved.getUpdatedAt());
	}
	
	private void testForJavaTimeApi() {
		log.info("===============================testForTimezone========================================");
		/*
			https://stackoverflow.com/questions/32833896/does-java-8s-new-java-date-time-api-take-care-of-dst
		 */
		LocalDateTime localDateTime
				= LocalDateTime.of(2015, Month.NOVEMBER, 1, 1, 0);
		LocalDateTime localDateTimeOneHourLater = localDateTime.plusHours(1);
		log.info("localDateTime : " + localDateTime);                                                //2015-11-01T01:00
		log.info("after +1 hours localDateTimeOneHourLater : " + localDateTimeOneHourLater);        //2015-11-01T02:00
		
		//Day light saving time이 적용 됨
		ZoneId zoneId = ZoneId.of("America/Los_Angeles");
		ZonedDateTime zonedDateTime
				= ZonedDateTime.of(2015, 11, 1, 1, 0, 0, 0, zoneId);
		log.info("zonedDateTime : " + zonedDateTime);                                            //2015-11-01T01:00-07:00[America/Los_Angeles]
		log.info("after +1 hours of zonedDateTime : " + zonedDateTime.plusHours(1));            //2015-11-01T01:00-08:00[America/Los_Angeles]
		
		//단순히 offset만 적용 됨
		OffsetDateTime offsetDateTime
				= OffsetDateTime.of(2015, 11, 1, 1, 0, 0, 0, zoneId.getRules().getOffset(localDateTime));
		log.info("offsetDateTime : " + offsetDateTime);                                            //2015-11-01T01:00-07:00
		log.info("after +1 hours of offsetDateTime : " + offsetDateTime.plusHours(1));            //2015-11-01T02:00-07:00
	}
}
