package me.strongwhisky.app.day28.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.Random;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-06-11
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfig {
	
	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.of("User::" + new Random().nextLong());
	}
}
