package com.techproeducation.BackendProject.initialwork;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContactMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactMessageApplication.class, args);
        //this is our first day
        //this is the first test
    }

}









