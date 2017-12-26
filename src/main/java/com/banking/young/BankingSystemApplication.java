package com.banking.young;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BankingSystemApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(BankingSystemApplication.class, args);

//        for (String name : context.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
    }

}
