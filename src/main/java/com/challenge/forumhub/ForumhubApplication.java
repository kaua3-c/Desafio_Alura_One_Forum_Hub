package com.challenge.forumhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ForumhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumhubApplication.class, args);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(
                encoder.matches("123456", "$2a$10$f7Bw1DBDO0uvZGfExv28bu.GYWOzHRoWfT8b5M0Y7WIQkwGJ0sxAy")
        );
	}

}
