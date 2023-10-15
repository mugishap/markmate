package rw.ac.rca.marking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"javax.servlet"})
public class MarkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarkingApplication.class, args);
    }

}
