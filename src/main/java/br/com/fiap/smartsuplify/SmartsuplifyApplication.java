package br.com.fiap.smartsuplify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SmartsuplifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartsuplifyApplication.class, args);
	}

}
