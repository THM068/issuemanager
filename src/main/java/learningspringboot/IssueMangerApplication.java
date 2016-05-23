package learningspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

@SpringBootApplication
@DependsOn("databaseLoader")
public class IssueMangerApplication {

    @Autowired
    DatabaseLoader databaseLoader;

	@Autowired
	TeammateRepository teammateRepository;
	private static final Logger log = LoggerFactory.getLogger(IssueMangerApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(IssueMangerApplication.class, args);
	}

	@PostConstruct
	void seeTheRoster() {
		for (Teammate teammate : teammateRepository.findAll()) {
			log.info(teammate.toString());
		}
	}
}
