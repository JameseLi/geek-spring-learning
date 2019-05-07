package com.grouk.webfluxdemo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author lizhengjun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String name;

    private String phone;

    private Date birthday;
}
