package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}



//http://localhost:8080/evaluate-rule?json=%7B%22age%22%3A%2231%22%2C%22department%22%3A%22Sales%22%2C%22experience%22%3A%2210%22%2C%22salary%22%3A%2210000%22%7D&id=67152aaa39c8933f77131a25
