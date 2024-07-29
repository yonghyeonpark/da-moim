package community.da_moim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DaMoimApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaMoimApplication.class, args);
    }
}
