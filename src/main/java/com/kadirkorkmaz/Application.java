package com.kadirkorkmaz;

import org.pegdown.PegDownProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    /*
     // Markdown processor to convert from HTML to Markdown
     // and Markdown ot HTML 
     // https://github.com/sirthias/pegdown
     */
    @Bean
    public PegDownProcessor getPegDownProcessor() {
        return new PegDownProcessor();
    }

}
