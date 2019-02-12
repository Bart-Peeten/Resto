package be.pxl.s2it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*n@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}) */
@SpringBootApplication
public class RestoMain extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(RestoMain.class, args);
        System.out.println("PASSWORD: " +
            new BCryptPasswordEncoder().encode("bart"));
    }
}
