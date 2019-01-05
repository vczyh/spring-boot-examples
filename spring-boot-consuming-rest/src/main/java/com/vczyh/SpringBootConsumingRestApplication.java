package com.vczyh;

import com.vczyh.entity.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootConsumingRestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate template(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner runnner(RestTemplate restTemplate) {
        return args -> {
            Quote quote = restTemplate.getForObject(
                    "https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info(quote.toString());
        };
    }

}

