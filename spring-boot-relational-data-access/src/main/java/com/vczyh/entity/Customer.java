package com.vczyh.entity;

import lombok.Data;

@Data
public class Customer {

    private long  id;
    private String firstName;
    private String lastName;

    // @Data注解不会自动生成多参数构造方法
    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
