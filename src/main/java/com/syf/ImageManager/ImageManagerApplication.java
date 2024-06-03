package com.syf.ImageManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.syf.ImageManager.model.User;

@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class ImageManagerApplication {

	@RequestMapping("/")
	@ResponseBody
	public static void main(String[] args) {
		SpringApplication.run(ImageManagerApplication.class, args);
		createUsers();
	}

	public static void createUsers() {
		
		RestTemplate restTemplate = new RestTemplate();

        for (int i = 0; i < 10; i++) {
            User request = new User();
            request.setUsername("user" + i);
            request.setPassword("password" + i);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<User> entity = new HttpEntity<>(request, headers);

            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8090/api/users/register", entity, String.class);

            System.out.println("Response for user " + i + ": " + response.getBody());
        }
	}
	
}
