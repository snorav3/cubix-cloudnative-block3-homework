package hu.cubix.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SecondHomeworkFrontapp {

	public static void main(String[] args) {
		SpringApplication.run(SecondHomeworkFrontapp.class, args);
	}

}
