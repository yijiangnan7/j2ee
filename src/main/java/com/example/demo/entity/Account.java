package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String userName;
    private String password;
    private Date createdTime; // 将 createdTime 属性的类型修改为 java.util.Date

    // Getters and setters
}