package net.openobject.ekko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
@EnableCaching
public class EkkoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkkoServerApplication.class, args);
	}

}
