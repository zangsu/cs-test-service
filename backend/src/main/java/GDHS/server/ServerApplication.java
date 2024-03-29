package GDHS.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
public class ServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
