package com.project.RaiseComplaint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaiseComplaintApplication {

	public static void main(String[] args) {

		SpringApplication.run(RaiseComplaintApplication.class, args);

		checkEnv();
	}
	public static void checkEnv() {
		System.out.println("MAIL_USERNAME = " + System.getenv("MAIL_USERNAME"));
		System.out.println("MAIL_PASSWORD = " + System.getenv("MAIL_PASSWORD"));
		System.out.println("API_SECRET_KEY = " + System.getenv("API_SECRET_KEY"));
	}
}
