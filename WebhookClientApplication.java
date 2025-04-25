package bajaj.com.finserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bajaj.com.finserv.service.WebhookService;

import org.springframework.boot.CommandLineRunner;



@SpringBootApplication
public class WebhookClientApplication {
	public static void main(String[] args) {
        SpringApplication.run(WebhookClientApplication.class, args);
    }

    @Bean
    CommandLineRunner run(WebhookService service) {
        return args -> service.execute();
    }
}