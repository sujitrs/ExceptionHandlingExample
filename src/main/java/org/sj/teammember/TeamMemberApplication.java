package org.sj.teammember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@SpringBootApplication
public class TeamMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamMemberApplication.class, args);
	}

}
