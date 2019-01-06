package com.vczyh;

import com.vczyh.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class SpringBootRelationalDataAccessApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRelationalDataAccessApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("drop table customers if exists ");
        jdbcTemplate.execute("create table customers (id serial ,first_name varchar(255),last_name varchar(255))");

        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        splitUpNames.forEach(name -> log.info(String.format("inserting customer record for %s %s", name[0], name[1])));

        // batchUpdate operation
        jdbcTemplate.batchUpdate("insert into customers(first_name,last_name) values(?,?)", splitUpNames);

        log.info("querying for customers where first_name is Josh");

        jdbcTemplate.query("select * from customers where first_name = ?", new Object[]{"Josh"},
                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name")))
                .forEach(customer -> log.info(customer.toString()));
    }
}

