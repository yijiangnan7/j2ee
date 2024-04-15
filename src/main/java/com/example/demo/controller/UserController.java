package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.UserService;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/insert")
    public ResponseEntity<String> insertUser(@RequestParam("id") Long id, @RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam(required = false) String createdTimeStr) {
        try {
            Account account = Account.builder()
                    .id(id)
                    .userName(userName)
                    .password(password)
                    .build();

            // 设置 createdTime 字段，如果提供了 createdTime 参数
            if (createdTimeStr != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createdTime = LocalDateTime.parse(createdTimeStr, formatter);
                Date date = Date.from(createdTime.atZone(ZoneId.systemDefault()).toInstant());
                account.setCreatedTime(date);
            }

            userService.insert(account); // 调用 UserService 的 insert 方法插入数据
            return ResponseEntity.ok("User inserted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inserting user.");
        }
    }





    @GetMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam("id") Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok("User deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user.");
        }
    }

    @GetMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam("id") Long id, @RequestParam("userName") String userName, @RequestParam("password") String password, @RequestParam(required = false) String createdTimeStr) {
        try {
            Account account = Account.builder()
                    .id(id)
                    .userName(userName)
                    .password(password)
                    .build();

            // 设置 createdTime 字段，如果提供了 createdTime 参数
            if (createdTimeStr != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createdTime = LocalDateTime.parse(createdTimeStr, formatter);
                Date date = Date.from(createdTime.atZone(ZoneId.systemDefault()).toInstant());
                account.setCreatedTime(date);
            }

            userService.update(account);
            return ResponseEntity.ok("User updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user.");
        }
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam(value = "id", required = false) Long id,
                                        @RequestParam(value = "userName", required = false) String userName) {
        try {
            List<Account> users;

            // 如果提供了id参数，则按id查询
            if (id != null) {
                Account user = userService.getUserById(id);
                if (user != null) {
                    users = Collections.singletonList(user);
                } else {
                    users = Collections.emptyList();
                }
            }
            // 如果提供了userName参数，则按userName查询
            else if (userName != null) {
                users = userService.searchUsersByUserName(userName);
            }
            // 如果既没有提供id参数也没有提供userName参数，则返回所有用户
            else {
                users = userService.getAllUsers();
            }

            if (!users.isEmpty()) {
                StringBuilder responseText = new StringBuilder();
                for (Account user : users) {
                    responseText.append(user.toString()).append("\n");
                }
                return ResponseEntity.ok("Query successful!\n" + responseText.toString());
            } else {
                return ResponseEntity.ok("No users found for the given criteria.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error querying users.");
        }
    }

}