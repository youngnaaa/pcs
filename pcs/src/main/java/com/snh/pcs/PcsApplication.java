package com.snh.pcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //DB자동연결 해제 
public class PcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcsApplication.class, args);
	}

}
