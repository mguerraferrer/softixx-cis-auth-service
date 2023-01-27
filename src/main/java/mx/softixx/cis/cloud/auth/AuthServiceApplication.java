package mx.softixx.cis.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan({
	"mx.softixx.cis.common.user.persistence.model", 
	"mx.softixx.cis.cloud.auth.persistence.model"
})
@EnableJpaRepositories({
	"mx.softixx.cis.common.user.persistence.repository",
	"mx.softixx.cis.cloud.auth.persistence.repository"
})
@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}