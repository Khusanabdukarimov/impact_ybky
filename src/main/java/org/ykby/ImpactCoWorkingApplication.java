package org.ykby;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ImpactCoWorkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImpactCoWorkingApplication.class, args);
	}

}
