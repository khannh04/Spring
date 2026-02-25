package vn.hoidanit.springsieutoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringsieutocApplication {

    public static void main(String[] args) {

        // container
        ApplicationContext khannh = SpringApplication.run(SpringsieutocApplication.class, args);
        for (String s : khannh.getBeanDefinitionNames()) {
            System.out.println(s);
        }
    }

}
