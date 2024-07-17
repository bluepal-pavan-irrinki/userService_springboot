package Job_application.UserService;

import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;




@EnableFeignClients
@SpringBootApplication
@EntityScan(basePackages = "Job_application.UserService.UserEntity")

public class UserServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServicesApplication.class, args);
	}
	
@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
		
	}
	

	

}
