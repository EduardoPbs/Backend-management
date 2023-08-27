package my.system.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;

@SpringBootApplication
@ComponentScan({"my.system.management"})
public class ManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void Listener(){
		System.out.println("App Started at " + LocalDateTime.now());
	}
}
