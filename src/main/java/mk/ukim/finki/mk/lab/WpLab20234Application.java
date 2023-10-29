package mk.ukim.finki.mk.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WpLab20234Application {

    public static void main(String[] args) {
        SpringApplication.run(WpLab20234Application.class, args);
    }

}
