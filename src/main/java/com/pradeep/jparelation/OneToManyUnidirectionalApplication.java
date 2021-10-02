package com.pradeep.jparelation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OneToManyUnidirectionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneToManyUnidirectionalApplication.class, args);
	}

}
