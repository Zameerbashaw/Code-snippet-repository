package nathanda.com.Code.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CodeQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeQueryApplication.class, args);
	}

}
