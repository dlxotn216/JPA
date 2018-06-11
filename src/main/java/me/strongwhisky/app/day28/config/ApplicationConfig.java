package me.strongwhisky.app.day28.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-11
 */
@Configuration
public class ApplicationConfig {
	
	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.from(ZoneOffset.UTC)));
	}
	
}
