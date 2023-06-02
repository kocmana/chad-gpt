package at.kocmana.chadgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ChadGptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChadGptApplication.class, args);
    }

}
